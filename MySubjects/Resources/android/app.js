var Alloy = require("alloy"), _ = Alloy._, Backbone = Alloy.Backbone;

!function() {
    Alloy.Globals.drawer = void 0;
    Alloy.Globals.contentView = void 0;
    var currentCtrl;
    Alloy.Globals.optionsMenu = function(e) {
        currentCtrl.trigger("createOptionsMenu", e);
    };
    Alloy.Globals.open = function(_ctrl) {
        if (currentCtrl) {
            Alloy.Globals.contentView.remove(currentCtrl.getView());
            _.isFunction(currentCtrl.destroy) && currentCtrl.destroy();
        }
        currentCtrl = _ctrl;
        Alloy.Globals.contentView.add(currentCtrl.getView());
        currentCtrl.init();
    };
}();

Alloy.createController("index");