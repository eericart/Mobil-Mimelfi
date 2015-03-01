function WPATH(s) {
    var index = s.lastIndexOf("/");
    var path = -1 === index ? "com.jpntex.navdrawer/" + s : s.substring(0, index) + "/com.jpntex.navdrawer/" + s.substring(index + 1);
    return true && 0 !== path.indexOf("/") ? "/" + path : path;
}

module.exports = [ {
    isApi: true,
    priority: 1000.0001,
    key: "Window",
    style: {
        navBarHidden: true
    }
} ];