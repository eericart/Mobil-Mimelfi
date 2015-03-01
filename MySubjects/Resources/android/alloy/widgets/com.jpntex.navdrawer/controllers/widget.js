function WPATH(s) {
    var index = s.lastIndexOf("/");
    var path = -1 === index ? "com.jpntex.navdrawer/" + s : s.substring(0, index) + "/com.jpntex.navdrawer/" + s.substring(index + 1);
    return true && 0 !== path.indexOf("/") ? "/" + path : path;
}

function __processArg(obj, key) {
    var arg = null;
    if (obj) {
        arg = obj[key] || null;
        delete obj[key];
    }
    return arg;
}

function Controller() {
    function PxToDpi(px) {
        return px / pxdpi;
    }
    new (require("alloy/widget"))("com.jpntex.navdrawer");
    this.__widgetId = "com.jpntex.navdrawer";
    require("alloy/controllers/BaseController").apply(this, Array.prototype.slice.call(arguments));
    this.__controllerPath = "widget";
    if (arguments[0]) {
        __processArg(arguments[0], "__parentSymbol");
        __processArg(arguments[0], "$model");
        __processArg(arguments[0], "__itemTemplate");
    }
    var $ = this;
    var exports = {};
    exports.destroy = function() {};
    _.extend($, $.__views);
    var args = arguments[0] || {};
    var pxdpi = Ti.Platform.displayCaps.dpi / 160;
    var marginTop = 0;
    var defaults = {
        menuWidth: 250,
        ledge: 40,
        duration: 120,
        overlayShadow: true,
        win: null,
        mainView: null,
        menuView: null
    };
    args.children && _.each(args.children, function(child) {
        if (!child) return;
        var role = child.role;
        if (role) {
            "menu" === role && (defaults.menuView = child);
            "main" === role && (defaults.mainView = child);
        }
    });
    delete args.id;
    delete args.__parentSymbol;
    delete args.children;
    var options = _.extend({}, defaults, args);
    var win = options.win ? options.win : Ti.UI.createWindow({
        navBarHidden: true,
        backgroundColor: "#F1F1F1",
        barColor: "#F1F1F1",
        exitOnClose: false
    }), mainView = options.mainView ? options.mainView : Ti.UI.createView({
        width: "100%",
        height: Ti.UI.FILL,
        top: 0,
        left: 0,
        backgroundColor: "#eee"
    }), menuView = options.menuView ? options.menuView : Ti.UI.createView({
        height: Ti.UI.FILL,
        top: marginTop,
        left: 0,
        backgroundColor: "#333"
    }), currentView = null;
    mainView.width = "100%";
    mainView.setZIndex(3);
    menuView.setZIndex(1);
    menuView.setLeft(0);
    menuView.setWidth(options.menuWidth);
    var animating = false, menuOpen = false, curX = 0, deltaX = 0, halfWidth = options.menuWidth / 2, opacityRatio = .8 / options.menuWidth, moveHistory = {
        history: [],
        set: function(pointX) {
            var dateNow = Date.now();
            this.history.push({
                time: dateNow,
                x: pointX
            });
            while (this.history.length > 0 && this.history[0].time + 300 < dateNow) this.history.shift();
            if (this.history.length) {
                this.xVelocity = (pointX - this.history[0].x) / (dateNow - this.history[0].time);
                isNaN(this.xVelocity) && (this.xVelocity = 0);
            } else this.xVelocity = 0;
            this.x = pointX;
        }
    }, menuWrapper = Ti.UI.createView({
        width: options.ledge,
        height: Ti.UI.FILL,
        top: marginTop,
        left: 0,
        zIndex: 5
    }), menuOverlay = Ti.UI.createView({
        width: options.menuWidth,
        height: Ti.UI.FILL,
        top: marginTop,
        left: 0,
        zIndex: 2,
        touchEnabled: true
    });
    if (options.overlayShadow) {
        menuOverlay.setBackgroundColor("#000");
        menuOverlay.setOpacity(.8);
    }
    var touchStartHandler = function(e) {
        if (!animating) {
            var x = PxToDpi(e.x);
            moveHistory.set(x);
            curX = x - mainView.left;
        }
    };
    var touchMoveHandler = function(e) {
        if (!animating) {
            var x = PxToDpi(e.x);
            var tdeltaX = x - curX;
            moveHistory.set(x);
            if (tdeltaX <= options.menuWidth && tdeltaX >= 0) {
                mainView.left = tdeltaX;
                deltaX = tdeltaX;
                if (options.overlayShadow) {
                    var opacity = .8 - opacityRatio * deltaX;
                    menuOverlay.animate({
                        opacity: opacity,
                        duration: 0
                    });
                }
            }
            if (0 > tdeltaX) {
                mainView.left = 0;
                deltaX = 0;
            }
            if (tdeltaX > options.menuWidth) {
                mainView.left = options.menuWidth;
                deltaX = options.menuWidth;
            }
        }
    };
    var touchEndHandler = function(e) {
        if (!animating) {
            var x = PxToDpi(e.x);
            moveHistory.set(x);
            if (Math.abs(moveHistory.xVelocity) > .15) moveHistory.xVelocity > 0 ? openMenu() : closeMenu(); else {
                if (0 !== deltaX && deltaX !== -options.menuWidth) {
                    deltaX >= halfWidth && openMenu();
                    halfWidth > deltaX && closeMenu();
                }
                0 === deltaX && setClosedMenuVars();
                deltaX === options.menuWidth && setOpenMenuVars();
            }
        }
        moveHistory.history = [];
    };
    var setOpenMenuVars = function() {
        mainView.left = options.menuWidth;
        deltaX = options.menuWidth;
        menuWrapper.width = Ti.Platform.displayCaps.platformWidth - options.menuWidth;
        menuWrapper.left = options.menuWidth;
        menuOverlay.setTouchEnabled(false);
        menuOpen || menuWrapper.addEventListener("singletap", closeMenu);
        animating = false;
        menuOpen = true;
    };
    var setClosedMenuVars = function() {
        mainView.left = 0;
        deltaX = 0;
        menuWrapper.width = options.ledge;
        menuWrapper.left = 0;
        menuOverlay.setTouchEnabled(true);
        menuOpen && menuWrapper.removeEventListener("singletap", closeMenu);
        animating = false;
        menuOpen = false;
    };
    var openMenu = function() {
        if (!animating) {
            animating = true;
            options.overlayShadow && menuOverlay.animate({
                opacity: 0,
                duration: options.duration
            });
            mainView.animate({
                left: options.menuWidth,
                duration: options.duration
            }, setOpenMenuVars);
        }
    };
    var closeMenu = function() {
        if (!animating) {
            animating = true;
            options.overlayShadow && menuOverlay.animate({
                opacity: .8,
                duration: options.duration
            });
            mainView.animate({
                left: 0,
                duration: options.duration
            }, setClosedMenuVars);
        }
    };
    menuWrapper.addEventListener("touchstart", touchStartHandler);
    menuWrapper.addEventListener("touchmove", touchMoveHandler);
    menuWrapper.addEventListener("touchend", touchEndHandler);
    win.add(menuOverlay);
    win.add(menuWrapper);
    win.add(mainView);
    win.add(menuView);
    win.addEventListener("close", function() {
        menuWrapper.removeEventListener("touchstart", touchStartHandler);
        menuWrapper.removeEventListener("touchmove", touchMoveHandler);
        menuWrapper.removeEventListener("touchend", touchEndHandler);
    });
    exports.changeView = function(view) {
        if (null === currentView) {
            currentView = view;
            mainView.add(view);
        } else if (view.id !== currentView.id) {
            mainView.remove(currentView);
            currentView = view;
            mainView.add(view);
        }
        menuOpen && closeMenu();
    };
    exports.toggleMenu = function() {
        menuOpen ? closeMenu() : openMenu();
    };
    _.each([ "open", "close" ], function(fn) {
        exports[fn] || (exports[fn] = function() {
            return win[fn]();
        });
    });
    exports.on = function(event, callback) {
        return win.addEventListener(event, callback);
    };
    exports.off = function(event, callback) {
        return win.removeEventListener(event, callback);
    };
    exports.trigger = function(event, args) {
        return win.fireEvent(event, args);
    };
    exports.addEventListener = exports.on;
    exports.removeEventListener = exports.off;
    exports.fireEvent = exports.trigger;
    _.extend($, exports);
}

var Alloy = require("alloy"), Backbone = Alloy.Backbone, _ = Alloy._;

module.exports = Controller;