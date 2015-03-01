function __processArg(obj, key) {
    var arg = null;
    if (obj) {
        arg = obj[key] || null;
        delete obj[key];
    }
    return arg;
}

function Controller() {
    function __alloyId1() {
        $.__views.explore.removeEventListener("open", __alloyId1);
        if ($.__views.explore.activity) {
            $.__views.explore.activity.actionBar.displayHomeAsUp = true;
            $.__views.explore.activity.actionBar.onHomeIconItemSelected = closewin;
        } else {
            Ti.API.warn("You attempted to access an Activity on a lightweight Window or other");
            Ti.API.warn("UI component which does not have an Android activity. Android Activities");
            Ti.API.warn("are valid with only windows in TabGroups or heavyweight Windows.");
        }
    }
    function toggleMenu() {
        Alloy.Globals.navDrawer.toggleMenu();
    }
    require("alloy/controllers/BaseController").apply(this, Array.prototype.slice.call(arguments));
    this.__controllerPath = "calendar";
    if (arguments[0]) {
        {
            __processArg(arguments[0], "__parentSymbol");
        }
        {
            __processArg(arguments[0], "$model");
        }
        {
            __processArg(arguments[0], "__itemTemplate");
        }
    }
    var $ = this;
    var exports = {};
    var __defers = {};
    $.__views.explore = Ti.UI.createWindow({
        navBarHidden: true,
        backgroundColor: "#ecf0f1",
        id: "explore"
    });
    $.__views.explore && $.addTopLevelView($.__views.explore);
    $.__views.explore.addEventListener("open", __alloyId1);
    $.__views.__alloyId2 = Ti.UI.createLabel({
        color: "#3498db",
        font: {
            fontSize: "32sp",
            fontWeight: "bold"
        },
        text: "Explore View",
        id: "__alloyId2"
    });
    $.__views.explore.add($.__views.__alloyId2);
    $.__views.__alloyId3 = Ti.UI.createButton({
        bottom: "100",
        title: "Toggle Drawer",
        id: "__alloyId3"
    });
    $.__views.explore.add($.__views.__alloyId3);
    toggleMenu ? $.__views.__alloyId3.addEventListener("click", toggleMenu) : __defers["$.__views.__alloyId3!click!toggleMenu"] = true;
    exports.destroy = function() {};
    _.extend($, $.__views);
    __defers["$.__views.__alloyId3!click!toggleMenu"] && $.__views.__alloyId3.addEventListener("click", toggleMenu);
    _.extend($, exports);
}

var Alloy = require("alloy"), Backbone = Alloy.Backbone, _ = Alloy._;

module.exports = Controller;