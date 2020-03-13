//被点中的菜单标红  
function showMenu(PATH){

	//找元素 
	var href = window.location.href;
	var host = window.location.host;
	var index = href.indexOf(host);
	var path = href.substring(index + host.length);
	 
	var pathAddress = path.substring(PATH.length);
	
	var alink = $(".list-group a[href*='"+pathAddress+"']")
	//标红 
	alink.css("color","red");
	
	//展开
	//1.找元素 2.控制样式 
	alink.parent().parent().parent().removeClass("tree-closed");
	alink.parent().parent().show();	
	
}