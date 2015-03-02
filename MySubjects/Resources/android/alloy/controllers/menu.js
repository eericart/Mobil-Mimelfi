function __processArg(obj, key) {
    var arg = null;
    if (obj) {
        arg = obj[key] || null;
        delete obj[key];
    }
    return arg;
}

function Controller() {
    function onSelect(e) {
        if (e.row !== selected) {
            selectRow(e.row);
            _.defer(function() {
                Alloy.Globals.drawer.toggleLeftWindow();
            });
        }
    }
    function selectRow(_row) {
        selected && selected.setActive(false);
        selected = _row;
        selected.setActive(true);
        _.defer(function() {
            Alloy.Globals.open(Alloy.createController(_row.controller, {
                parent: args.parent
            }));
        });
    }
    require("alloy/controllers/BaseController").apply(this, Array.prototype.slice.call(arguments));
    this.__controllerPath = "menu";
    if (arguments[0]) {
        var __parentSymbol = __processArg(arguments[0], "__parentSymbol");
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
    var __alloyId2 = [];
    $.__views.cover = Alloy.createController("menurow", {
        height: 200,
        width: "fill",
        id: "cover",
        controller: "main",
        __parentSymbol: __parentSymbol
    });
    $.__views.__alloyId6 = Alloy.createController("menurow", {
        title: "Subjects",
        controller: "main",
        id: "__alloyId6",
        __parentSymbol: __parentSymbol
    });
    $.__views.__alloyId9 = Alloy.createController("menurow", {
        title: "Calendar",
        controller: "calendar",
        id: "__alloyId9",
        __parentSymbol: __parentSymbol
    });
    $.__views.__alloyId12 = Alloy.createController("menurow", {
        title: "About",
        controller: "about",
        id: "__alloyId12",
        __parentSymbol: __parentSymbol
    });
    $.__views.sectionMenu = Ti.UI.createTableViewSection({
        id: "sectionMenu"
    });
    __alloyId2.push($.__views.sectionMenu);
    $.__views.cover = Alloy.createController("menurow", {
        height: 200,
        width: "fill",
        id: "cover",
        controller: "main",
        __parentSymbol: __parentSymbol
    });
    $.__views.sectionMenu.add($.__views.cover.getViewEx({
        recurse: true
    }));
    $.__views.__alloyId6 = Alloy.createController("menurow", {
        title: "Subjects",
        controller: "main",
        id: "__alloyId6",
        __parentSymbol: __parentSymbol
    });
    $.__views.sectionMenu.add($.__views.__alloyId6.getViewEx({
        recurse: true
    }));
    $.__views.__alloyId9 = Alloy.createController("menurow", {
        title: "Calendar",
        controller: "calendar",
        id: "__alloyId9",
        __parentSymbol: __parentSymbol
    });
    $.__views.sectionMenu.add($.__views.__alloyId9.getViewEx({
        recurse: true
    }));
    $.__views.__alloyId12 = Alloy.createController("menurow", {
        title: "About",
        controller: "about",
        id: "__alloyId12",
        __parentSymbol: __parentSymbol
    });
    $.__views.sectionMenu.add($.__views.__alloyId12.getViewEx({
        recurse: true
    }));
    onSelect ? $.__views.sectionMenu.addEventListener("click", onSelect) : __defers["$.__views.sectionMenu!click!onSelect"] = true;
    $.__views.menu = Ti.UI.createTableView({
        data: __alloyId2,
        backgroundColor: "#002e3e",
        id: "menu"
    });
    $.__views.menu && $.addTopLevelView($.__views.menu);
    exports.destroy = function() {};
    _.extend($, $.__views);
    var args = arguments[0] || {};
    var selected;
    exports.select = function(_index) {
        selectRow(_.first($.menu.getData()).getRows()[_index]);
    };
    __defers["$.__views.sectionMenu!click!onSelect"] && $.__views.sectionMenu.addEventListener("click", onSelect);
    _.extend($, exports);
}

var Alloy = require("alloy"), Backbone = Alloy.Backbone, _ = Alloy._;

module.exports = Controller;