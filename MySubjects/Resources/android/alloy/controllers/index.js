function __processArg(obj, key) {
    var arg = null;
    if (obj) {
        arg = obj[key] || null;
        delete obj[key];
    }
    return arg;
}

function Controller() {
    function openSubject() {
        var view = Alloy.createController("subjects").getView();
        $.navDrawer.changeView(view);
    }
    function openCalendar() {
        var view = Alloy.createController("calendar").getView();
        $.navDrawer.changeView(view);
    }
    function openHome(e) {
        e.cancelBubble = true;
        openSubject();
    }
    require("alloy/controllers/BaseController").apply(this, Array.prototype.slice.call(arguments));
    this.__controllerPath = "index";
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
    $.__views.menu = Ti.UI.createView({
        height: Ti.UI.FILL,
        backgroundColor: "#29444e",
        id: "menu",
        role: "menu"
    });
    var __alloyId4 = [];
    $.__views.__alloyId5 = Ti.UI.createTableViewRow({
        height: 50,
        id: "__alloyId5"
    });
    __alloyId4.push($.__views.__alloyId5);
    openSubject ? $.__views.__alloyId5.addEventListener("click", openSubject) : __defers["$.__views.__alloyId5!click!openSubject"] = true;
    $.__views.__alloyId6 = Ti.UI.createView({
        top: 0,
        width: Ti.UI.FILL,
        height: "1px",
        backgroundColor: "#3a606f",
        touchEnabled: false,
        id: "__alloyId6"
    });
    $.__views.__alloyId5.add($.__views.__alloyId6);
    $.__views.__alloyId7 = Ti.UI.createLabel({
        left: 15,
        height: Ti.UI.SIZE,
        color: "#FFF",
        font: {
            fontWeight: "bold",
            fontSize: "16sp"
        },
        textAlign: Ti.UI.TEXT_ALIGNMENT_LEFT,
        touchEnabled: false,
        text: "Explore",
        id: "__alloyId7"
    });
    $.__views.__alloyId5.add($.__views.__alloyId7);
    $.__views.__alloyId8 = Ti.UI.createView({
        bottom: 0,
        width: Ti.UI.FILL,
        height: "1px",
        backgroundColor: "#142126",
        touchEnabled: false,
        id: "__alloyId8"
    });
    $.__views.__alloyId5.add($.__views.__alloyId8);
    $.__views.__alloyId9 = Ti.UI.createTableViewRow({
        height: 50,
        id: "__alloyId9"
    });
    __alloyId4.push($.__views.__alloyId9);
    openCalendar ? $.__views.__alloyId9.addEventListener("click", openCalendar) : __defers["$.__views.__alloyId9!click!openCalendar"] = true;
    $.__views.__alloyId10 = Ti.UI.createView({
        top: 0,
        width: Ti.UI.FILL,
        height: "1px",
        backgroundColor: "#3a606f",
        touchEnabled: false,
        id: "__alloyId10"
    });
    $.__views.__alloyId9.add($.__views.__alloyId10);
    $.__views.__alloyId11 = Ti.UI.createLabel({
        left: 15,
        height: Ti.UI.SIZE,
        color: "#FFF",
        font: {
            fontWeight: "bold",
            fontSize: "16sp"
        },
        textAlign: Ti.UI.TEXT_ALIGNMENT_LEFT,
        touchEnabled: false,
        text: "Shop",
        id: "__alloyId11"
    });
    $.__views.__alloyId9.add($.__views.__alloyId11);
    $.__views.__alloyId12 = Ti.UI.createView({
        bottom: 0,
        width: Ti.UI.FILL,
        height: "1px",
        backgroundColor: "#142126",
        touchEnabled: false,
        id: "__alloyId12"
    });
    $.__views.__alloyId9.add($.__views.__alloyId12);
    $.__views.menuTable = Ti.UI.createTableView({
        top: Alloy.Globals.marginTop,
        backgroundColor: "#29444e",
        separatorColor: "transparent",
        data: __alloyId4,
        id: "menuTable"
    });
    $.__views.menu.add($.__views.menuTable);
    $.__views.__alloyId13 = Ti.UI.createView({
        role: "main",
        id: "__alloyId13"
    });
    $.__views.navDrawer = Alloy.createWidget("com.jpntex.navdrawer", "widget", {
        overlayShadow: true,
        menuWidth: 250,
        duration: 200,
        ledge: 40,
        id: "navDrawer",
        children: [ $.__views.menu, $.__views.__alloyId13 ]
    });
    $.__views.navDrawer && $.addTopLevelView($.__views.navDrawer);
    exports.destroy = function() {};
    _.extend($, $.__views);
    $.navDrawer.open();
    openSubject();
    Alloy.Globals.navDrawer = $.navDrawer;
    $.navDrawer.on("android:back", openHome);
    __defers["$.__views.__alloyId5!click!openSubject"] && $.__views.__alloyId5.addEventListener("click", openSubject);
    __defers["$.__views.__alloyId9!click!openCalendar"] && $.__views.__alloyId9.addEventListener("click", openCalendar);
    _.extend($, exports);
}

var Alloy = require("alloy"), Backbone = Alloy.Backbone, _ = Alloy._;

module.exports = Controller;