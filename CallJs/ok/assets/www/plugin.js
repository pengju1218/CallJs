if (typeof cordova !== "undefined") {

	function testPlugin() {
		this._callback;
	}


	testPlugin.prototype.test = function(testData1, testData2, cb) {
		
		this._callback = cb;

		return cordova.exec(cb, null, 'test01', "test", [testData1, testData2]);
	};

	cordova.addConstructor(function() {
		if (!window.plugins) {
			window.plugins = {};
		}
		window.plugins.testPlugin = new testPlugin();
	});
};