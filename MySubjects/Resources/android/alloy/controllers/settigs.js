function __processArg(obj, key) {
    var arg = null;
    if (obj) {
        arg = obj[key] || null;
        delete obj[key];
    }
    return arg;
}

function Controller() {
    require("alloy/controllers/BaseController").apply(this, Array.prototype.slice.call(arguments));
    this.__controllerPath = "settigs";
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
    $.__views.settigs = Ti.UI.createWindow({
        id: "settigs"
    });
    $.__views.settigs && $.addTopLevelView($.__views.settigs);
    onOpen ? $.__views.settigs.addEventListener("open", onOpen) : __defers["$.__views.settigs!open!onOpen"] = true;
    $.__views.__alloyId14 = Ti.UI.createLabel({
        text: "New Window",
        id: "__alloyId14"
    });
    $.__views.settigs.add($.__views.__alloyId14);
    exports.destroy = function() {};
    _.extend($, $.__views);
    __defers["$.__views.settigs!open!onOpen"] && $.__views.settigs.addEventListener("open", onOpen);
    _.extend($, exports);
}

var Alloy = require("alloy"), Backbone = Alloy.Backbone, _ = Alloy._;

module.exports = Controller;