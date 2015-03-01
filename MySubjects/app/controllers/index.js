$.navDrawer.open();

// open first view
openSubject();

Alloy.Globals.navDrawer = $.navDrawer;

// menu
function openSubject() {
	var view = Alloy.createController('subjects').getView();
	$.navDrawer.changeView(view);
}

function openCalendar() {
	var view = Alloy.createController('calendar').getView();
	$.navDrawer.changeView(view);
}

function openHome(e) {
	e.cancelBubble = true;
	openSubject();
}

if (OS_ANDROID) {
	$.navDrawer.on('android:back', openHome);
}
