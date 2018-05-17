var essentialInfoForm = new Vue({
	el: '#essentialInfoForm',
	data: {

		fields: {
			resName: "",
			resCode: "",
			resProviderCode: "",
			resSystem: "",
			resDb: "",
			resAbstract: "",
			resFormatClassify: "",
			resFormatType: "",
			resShareClassify: "",
			resShareType: ""
		}
	},

	methods: {

		getObj: function() {
			return(this.fields);
		}
	}
});

var dataInfoForm = new Vue({
	el: '#dataInfoForm',
	data: {

		fields: {
			"dataName": "",
			"dataCode": "",
			"isPrimaryKey": "0",
			"dataSeqNo": "",
			"dataType": "varchar",
			"dataLength": "255",
			"dataPrecision": "",
			"dataMetaNum": "",
			"remark": ""
		},

		items: []
	},

	methods: {

		add: function() {

			if(this.fields.dataName == null || this.fields.dataName == "") {
				alert("数据项名称必须填写");
				return;
			}

			if(this.fields.dataCode == null || this.fields.dataCode == "") {
				alert("数据项代码必须填写");
				return;
			}

			if(this.fields.isPrimaryKey == null || this.fields.isPrimaryKey == "") {
				alert("是否主键必须填写");
				return;
			}

			//复制一个对象 否则修改表单会互相影响
			var target = JSON.parse(JSON.stringify(this.fields));
			this.items.push(target);

			//清空fields对象
			for(var f in this.fields) {
				//alert (this.fields[f]);
				this.fields[f] = "";
			}

			this.fields.isPrimaryKey = "0";
			this.fields.dataType = "varchar";
			this.fields.dataLength = "255";

		},

		del: function(index) {
			this.items.splice(index, 1);
		},

		getObj: function() {
			return(this.items);
		}

	}

});

var providerInfoForm = new Vue({
	el: '#providerInfoForm',
	data: {
		fields: {
			"provideType": "",
			"fileProtocol": "",
			"fileAddress": "",
			"netProtocol": "",
			"netURL": "",
			"netClientId": "",
			"netToken": "",
			"responseFormat": "",
			"responseDataNode": "",
			"dbType": "",
			"dbAddress": "",
			"dbPort": "",
			"dbUsername": "",
			"dbPassword": "",
			"dbInstance": "",
			"dbTable": "",
			"incrDatetime": "",
			"incrTimestamp": "",
			"netArgs": [],
			"resPersistence": "",
			"resFormat": "",
			"encoding": "",
			"isStored": "",
			"isStructured": "",
			"linesTerminated": "",
			"fieldsTerminated": "",
			"fieldsDelimited": "",
			"dateFormat": ""

		},

		"netArgs": {
			"argName": "",
			"argCode": "",
			"argType": "",
			"remark": ""
		}
	},

	methods: {

		add: function() {

			if(this.netArgs.argName == null || this.netArgs.argName == "") {
				alert("参数名称必须填写");
				return;
			}

			if(this.netArgs.argCode == null || this.netArgs.argCode == "") {
				alert("参数英文名称必须填写");
				return;
			}

			if(this.netArgs.argType == null || this.netArgs.argType == "") {
				alert("参数类型必须填写");
				return;
			}

			//复制一个对象 否则修改表单会互相影响
			var target = JSON.parse(JSON.stringify(this.netArgs));
			this.fields.netArgs.push(target);

			//清空netArgs对象
			for(var f in this.netArgs) {
				this.netArgs[f] = "";
			}

		},
		del: function(index) {
			this.fields.netArgs.splice(index, 1);
		},
		getObj: function() {
			return(this.fields);
		}

	}
});

function doSubmit() {

	var requiredField = {
		"信息资源名称": essentialInfoForm.fields.resName,
		"信息资源代码": essentialInfoForm.fields.resCode
		//		"提供方式": providerInfoForm.fields.provideType,
		//		"文件访问协议": providerInfoForm.fields.fileProtocol,
		//		"接口访问协议": providerInfoForm.fields.netProtocol
	}

	for(var f in requiredField) {

		if(requiredField[f] == null || requiredField[f] == "") {
			alert(f + "必须填写");
			throw "必填项" + f + "没有填写";
		}

	}

	var jsonObj = {};
	jsonObj.essentialInfo = essentialInfoForm.getObj();
	jsonObj.dataInfos = dataInfoForm.getObj();
	jsonObj.providerInfo = providerInfoForm.getObj();

	var jsonStr = JSON.stringify(jsonObj);

	$.ajax({
		type: 'post',
		url: '/manage/addRes?access_token=111111',
		dataType: 'json',
		data: jsonStr,
		contentType: 'application/json',
		success: function(result) {
			console.debug(result);
			alert("添加成功，版本号：" + result.version);
			window.location.href = "/manage/addResPre?access_token=111111";
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert("发生错误" + XMLHttpRequest + textStatus + errorThrown)
		}

	});

}