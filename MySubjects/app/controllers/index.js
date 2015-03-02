//initial Menu
var menu;

initDrawer();

//initializes drawer navigation
function initDrawer() {
  // Load module
  var TiDrawerLayout = require('com.tripvi.drawerlayout');

  // define menu and main content view
  menu = Alloy.createController('menu', {
    parent : $.index
  });

  // this is just a wrapper
  // actual content views are add to this later
  Alloy.Globals.contentView = Ti.UI.createView({
    width : Ti.UI.FILL,
    height : Ti.UI.FILL
  });

  Alloy.Globals.drawer = TiDrawerLayout.createDrawer({
    leftView : menu.getView(),
    centerView : Alloy.Globals.contentView,
    leftDrawerWidth : 240
  });

  Alloy.Globals.drawer.addEventListener('draweropen', onDrawerChange);
  Alloy.Globals.drawer.addEventListener('drawerclose', onDrawerChange);

  $.index.add(Alloy.Globals.drawer);

  $.index.open();
}

//Android view open event

function onOpen() {

  var activity = $.index.getActivity();

  if (activity) {

    var actionBar = activity.getActionBar();

    activity.onCreateOptionsMenu = function(e) {
      e.menu.clear();

      e.activity = activity;
      e.actionBar = actionBar;

      if (!Alloy.Globals.drawer.isLeftDrawerOpen) {
        // use a global method to forward this event to the nested controller
        Alloy.Globals.optionsMenu(e);
      } else {
        actionBar.title = "MySubjects";
      }

      e.menu.add({
        title : "Help",
        showAsAction : Ti.Android.SHOW_AS_ACTION_NEVER
      });

      e.menu.add({
        title : "Settings",
        showAsAction : Ti.Android.SHOW_AS_ACTION_NEVER
      });
    };
    if (actionBar) {
      actionBar.displayHomeAsUp = true;
      actionBar.title = "MySubjects";
      actionBar.onHomeIconItemSelected = function() {
        Alloy.Globals.drawer.toggleLeftWindow();
      };
    }
  };

  init();

  return true;
}

function onDrawerChange(e) {
  $.index.getActivity().invalidateOptionsMenu();
}

//initializes the Controller

function init() {
  menu.select(0);
  OS_ANDROID && $.index.getActivity().invalidateOptionsMenu();
}