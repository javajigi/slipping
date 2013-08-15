(function () {
	"use strict";
	
	if (typeof window.Slipp === "undefined") {
		window.Slipp = {};
	}
	
	Slipp.namespace = function (ns) {
		var parts = ns.split("."),
				parent = Slipp,
				i;

		if (parts[0] === "Slipp") {
				parts = parts.slice(1);
		}

		for (i = 0; i < parts.length; i += 1) {
				if (typeof parent[parts[i]] === "undefined") {
						parent[parts[i]] = {};
				}

				parent = parent[parts[i]];
		}
		return parent;
	};	
}());