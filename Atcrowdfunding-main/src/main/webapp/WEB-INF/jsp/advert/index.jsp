<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<link rel="stylesheet" href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/main.css">
	<link rel="stylesheet" href="${APP_PATH }/css/pagination.css">
	
	<style>
		.tree li {
			list-style-type: none;
			cursor: pointer;
		}
		
		table tbody tr:nth-child(odd) {
			background: #F4F4F4;
		}
		
		table tbody td:nth-child(even) {
			color: #C00;
		}
</style>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 广告管理</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <%@ include file="/WEB-INF/jsp/common/top.jsp" %>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
			<div class="tree">
				<ul style="padding-left:0px;" class="list-group">
					<!-- 树节点 -->
					<jsp:include page="/WEB-INF/jsp/common/menu.jsp"></jsp:include>
				</ul>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="panel panel-default">
			  <div class="panel-heading">
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
			  </div>
			  <div class="panel-body">
<form class="form-inline" role="form" style="float:left;">
  <div class="form-group has-feedback">
    <div class="input-group">
      <div class="input-group-addon">查询条件</div>
      <input id="queryText" class="form-control has-success" type="text" placeholder="请输入查询条件">
    </div>
  </div>
  <button id="queryBtn" type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
</form>
<button id="deleteBatchBtn" type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
<button class="btn btn-primary" style="float:right;" onclick="window.location.href='${APP_PATH}/advert/toAdd.htm'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
<br>
 <hr style="clear:both;">
          <div class="table-responsive">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th width="30">#</th>
                  <th width="30"><input id="allCheckbox" type="checkbox"></th>
                  <th>名称</th>
                  <th>地址</th>
                  <th>状态</th>
                  <th width="110">操作</th>
                </tr>
              </thead>
              <tbody>
                
              </tbody>
			  <tfoot>
			     <tr >
				     <td colspan="6" align="center">
<!-- 						<ul class="pagination"> 
								
 						</ul> -->
 						<div id="Pagination" class="pagination"> <!-- 这里显示分页 --> </div>
					 </td>
				 </tr>

			  </tfoot>
            </table>
          </div>
			  </div>
			</div>
        </div>
      </div>
    </div>

    <script type="text/javascript" src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${APP_PATH }/script/docs.min.js"></script>
	<script type="text/javascript" src="${APP_PATH }/jquery/layer/layer.js"></script>
	<script type="text/javascript" src="${APP_PATH }/jquery/pagination/jquery.min.js"></script>
	<script type="text/javascript" src="${APP_PATH }/jquery/pagination/jquery.pagination.js"></script>	
	
        <script type="text/javascript">
            $(function () {
			    $(".list-group-item").click(function(){
				    if ( $(this).find("ul") ) {
						$(this).toggleClass("tree-closed");
						if ( $(this).hasClass("tree-closed") ) {
							$("ul", this).hide("fast");
						} else {
							$("ul", this).show("fast");
						}
					}
				});
		    	queryPage(0);
		    	  
			    showMenu("${APP_PATH}");
            });
            
//             function changePageno( pageno ) {
<%--             	//window.location.href = "<%=request.getContextPath() %>/advert/index.htm?pageno="+pageno; --%>
//             	queryPage(pageno-1);
//             }
            
            var condition = false ;
            //模糊查询 
//             $("#queryBtn").click(function(){
// 				var queryText = $("#queryText");
            	
//             	if($.trim(queryText.val())==""){
//             		layer.msg("查询条件不能为空", {time:1000, icon:5, shift:6}, function(){
//             			queryText.focus();
//             		});
//             		return ;
//             	}
//             	condition = true;
//             	queryPage(0);
//             });
            
            //分页查询 
            var loadingIndex = -1;
            function queryPage( pageIndex ){
            	var dataObj = {
            			"pageno" : pageIndex+1 ,   //pageno 是属性名称,是否增加双引号无所谓
            			"pagesize" : 2
            	};
                
            	if(condition){
                	dataObj.pagetext = $("#queryText").val(); //增加模糊查询条件
                }

                $.ajax({
            		type : "POST",
            		data : dataObj,
            		dataType: "json",
            		url : "${APP_PATH}/advert/doIndex.do",
            		beforeSend : function(){
//             			loadingIndex = layer.load(2, {time: 10*1000});
            			loadingIndex = layer.msg('数据查询中', {icon: 6});
            			return true;	
            		},
            		success : function(result){
            			$("#allCheckbox").attr("checked" , false);
            			layer.close(loadingIndex);
            			if(result.success){
            				var pageObj = result.page;
            				var data = pageObj.datas;

            				var content = '';
            				
            				$.each(data, function(i,n){
            					content+='<tr>';
                				content+='  <td>'+(i+1)+'</td>';
                				content+='  <td><input type="checkbox" id="'+n.id+'"></td>';
                  				content+='  <td>'+n.name+'</td>';
                  				content+='  <td>'+n.url+'</td>';
                  				
                  				if(n.status == 0){
                  					content+='  <td>草稿</td>';
                  				}
                  				
                  				if(n.status == 1){
                  					content+='  <td>未审核</td>';
                  				}
                  				
                  				if(n.status == 2){
                  					content+='  <td>已审核</td>';
                  				}
                  				
                  				if(n.status == 3){
                  					content+='  <td>已发布</td>';
                  				}
      
                  				content+='  <td>';
                				content+='      <button type="button" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>';
                				content+='      <button type="button" onclick="window.location.href=\'${APP_PATH}/advert/toUpdate.htm?id='+(n.id)+'\'" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>';
                				content+='	    <button type="button" onclick="deleteAdvert('+n.id+',\''+n.name+'\')" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>';
                				content+='  </td>';
                				content+='</tr>';
            				});

            				$("tbody").html(content);
            				
            				//创建分页 
            				//此demo通过Ajax加载分页元素
            				var num_entries = pageObj.totalsize;
	            			$("#Pagination").pagination(num_entries, {
	            				num_edge_entries: 2, //边缘页数
	            				num_display_entries: 4, //主体页数
	            				callback: queryPage,//查询当前页的数据 
	            				items_per_page: pageObj.pagesize, //每页显示1项 
	            				current_page: (pageObj.pageno-1),//当前页,索引从0开始 
	            				prev_text: "上一页",
	            				next_text: "下一页"
	            			});
	            				
//             				layer.msg("需要进行局部刷新", {time:1000, icon:6, shift:6});
            			}else{
            				layer.msg("查询数据失败!", {time:1000, icon:5, shift:6});
            			}
            		},
            		error : function(){
            			layer.msg("加载数据失败!", {time:1000, icon:5, shift:6});
            		}
            		
            	});
            	
            }
            
            //删除 
            function deleteAdvert(id,name){
            	
            	layer.confirm("确认要删除这个["+name+"]用户吗?",  {icon: 3, title:'提示'}, function(cindex){
            		$.ajax({
    	            	type : "POST",
    	            	data : {
    	            		"id" : id
    	            	},
    	            	url : "${APP_PATH}/advert/doDelete.do",
    	            	beforeSend : function(){
    	
    	            		return true;
    	            	},
    	            	success : function(result){
    	            		if(result.success){
    	            			layer.msg("删除成功!", {time:1000, icon:6, shift:6});
    	            			queryPage(1);
    	            		}else{
    	            			layer.msg("删除失败!", {time:1000, icon:5, shift:6});
    	            		}
    	            	},
    	            	error : function(){
    	            		layer.msg("删除异常!", {time:1000, icon:5, shift:6});
    	            	}
    	            });
    			}, function(cindex){
    			    layer.close(cindex);
    			});
 
	         }
            
            //复选框 : 全选 
            	$("#allCheckbox").click(function(){
            	var checkedStatus =  this.checked;
            	//alert(checkedStatus);
            	
           		$("tbody tr td input[type='checkbox']").prop("checked" , checkedStatus);
            	//alert(checkedBoxAll.length);

            });
            
            //批量删除 
            $("#deleteBatchBtn").click(function(){

            	var selectCheckbox = $("tbody tr td input:checked");
				
            	if(selectCheckbox.length == 0){
            		layer.msg("至少选择一个用户进行删除,请选择用户!", {time:1000, icon:5, shift:6});
					return false;
				}
				
				var jsonObject = {};
				$.each(selectCheckbox,function(i,n){
					jsonObject["datasAdvert["+i+"].id"] = n.id;		
					
				});

				var index = -1;
				layer.confirm("确认要删除这些用户吗?",  {icon: 3, title:'提示'}, function(cindex){
					layer.close(cindex); 
					$.ajax({
    	            	type : "POST",
						data : jsonObject,
    	            	url : "${APP_PATH}/advert/doDeleteBatch.do",
    	            	beforeSend : function(){
    	            		index = layer.load(2, {time: 10*1000});
    	            		
    	            		return true;
    	            	},
    	            	success : function(result){
    	            		layer.close(index);
    	            		if(result.success){
    	            			layer.msg("删除成功!", {time:1000, icon:6, shift:6});
    	            			queryPage(1);
    	            		}else{
    	            			layer.msg("删除失败!", {time:1000, icon:5, shift:6});
    	            		}
    	            	},
    	            	error : function(){
    	            		layer.msg("删除异常!", {time:1000, icon:5, shift:6});
    	            	}
    	            });
    			}, function(cindex){
    			    layer.close(cindex);
    			});
            	
            	
            });
            
        </script>
        <script type="text/javascript" src="${APP_PATH }/script/menu.js"></script>
  </body>
</html>
    