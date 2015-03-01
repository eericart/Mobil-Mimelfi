var win = Ti.UI.createWindow({
    backgroundColor:'white',
    title: 'Material Theme'
});

var textField = Ti.UI.createTextField({
  width: 200,
  color: "black"
});

win.add(textField);
win.open();