function __processArg(obj, key) {
    var arg = null;
    if (obj) {
        arg = obj[key] || null;
        delete obj[key];
    }
    return arg;
}

function Controller() {
    function toggleMenu() {
        Alloy.Globals.navDrawer.toggleMenu();
    }
    require("alloy/controllers/BaseController").apply(this, Array.prototype.slice.call(arguments));
    this.__controllerPath = "subjects";
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
    $.__views.__alloyId14 = Ti.UI.createLabel({
        color: "#3498db",
        font: {
            fontSize: "32sp",
            fontWeight: "bold"
        },
        text: "Explore View",
        id: "__alloyId14"
    });
    $.__views.explore.add($.__views.__alloyId14);
    $.__views.__alloyId15 = Ti.UI.createButton({
        bottom: "100",
        title: "Toggle Drawer",
        id: "__alloyId15"
    });
    $.__views.explore.add($.__views.__alloyId15);
    toggleMenu ? $.__views.__alloyId15.addEventListener("click", toggleMenu) : __defers["$.__views.__alloyId15!click!toggleMenu"] = true;
    exports.destroy = function() {};
    _.extend($, $.__views);
    __defers["$.__views.__alloyId15!click!toggleMenu"] && $.__views.__alloyId15.addEventListener("click", toggleMenu);
    _.extend($, exports);
}

var Alloy = require("alloy"), Backbone = Alloy.Backbone, _ = Alloy._;

module.exports = Controller;