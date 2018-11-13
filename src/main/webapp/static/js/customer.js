
// 获取项目名  如果没有项目名会获取第一个URL路径
var pathName = window.document.location.pathname;
var contextPath = pathName.substring(0,pathName.substr(1).indexOf('/')+1);

if(contextPath=="/customer"){contextPath="";}
var totalPages;   //总页码
var current;  // 当前页码
var pageSize = 15;  // 每页显示多少
var pageNum  ; // 第几页

$(function(){

    initCustomerInfo(0,pageSize,null);
    pageInfo(null);

});

$('#pageSize').change(function(){
    pageSize = $('#pageSize').val();
    search( );
});

function search( ) {

    var firstName = $('#searchFirstName').val();
   if (firstName != null & firstName != ""){
       initCustomerInfo(1,pageSize,firstName);
   }else {
       initCustomerInfo(0,pageSize,null);
   }

}

function pageInfo(firstName) {

    $("#pagination").pagination({
        currentPage: 1,
        totalPages: totalPages,    //总页数
        isShow: false,
        count: 4,   // 页面显示内容
        prevPageText: "< 上一页",
        nextPageText: "下一页 >",
        callback: function(current) {

            $("#pagination").pagination("setPage", current, totalPages);   // 設置頁面
            pageNum = current;
            var info = $("#pagination").pagination("getPage");    // 獲取頁面
            initCustomerInfo(current,pageSize,firstName);
            $("#current").text(current)
        }
    });

}

			/**
			 *  初始化Customer表单信息
			 * @returns
			 */
	function  initCustomerInfo(pageNum,pageSize,firstName){

                var data = "pageNum="+pageNum+"&pageSize="+pageSize;
                if (firstName != null & firstName != "") {
                  data =data+"&firstName="+firstName;
                }
                $.ajax({
                    type:"POST",
                    data:data,
                    url:"/customer/getCustomerInfo.do",
                     success : function(data) {
                        if (data.flag==false) {
								layer.msg('查询失败！', {icon: 5});
								return false;
                        }

                        if(data.flag==true){
								var  customerData= data.customerList;

								totalPages = data.pagePages;  // 一共多少页

                            $('#pageCustomer').removeClass();
                             if(totalPages==1){   // 只有一页时 隐藏分页

                                 $("#pageCustomer").addClass("hidden");
                            }
								$("#totalPage").text(totalPages)

								var lastUpdate = "";

								var  htmlstr =""
								$.each(customerData,function(i,o){
                                   // console.log(customerData)
                                    if(o.lastUpdate==null){
                                        lastUpdate = " 无修改记录 " ;
                                    }else{
                                        lastUpdate = getFormatDate( o.lastUpdate );  	//日期格式化
                                    }

									htmlstr+=  "<tr>"
										+" <td> <a href='javascript:void(0);' onclick='updateCustomer( "+ o.customerId + ")'" +" >修改</a>  | "
										+"   <a href='javascript:void(0);' onclick='deleteCustomer( "+ o.customerId + ")'" +" >删除</a> </td>"
                                        +" <td>"+ o.firstName   +" </td>"
                                        +" <td>"+ o.lastName  +"</td>"
                                        +" <td>"+ o.address.address  +"</td>"
                                        +" <td>"+ o.email  +"</td>"
                                        +" <td>"+  o.customerId  +"</td>"
										+" <td>"+ lastUpdate +"</td>"
										+" </tr>"

								});
   								$("tbody").html(htmlstr);
                        }
                    },
                    error : function() {
                    	layer.msg('初始化失败！', {icon: 5});
                    }
                });   // ajax结束

		 }
/**
 * 打开添加页面
 */
function customerAdd() {
   // alert(contextPath+'/customer/create.do')
        window.location.href='/customer/create.do';
    }


address();  // 加载地址信息

	 /**
	  * -----------------------------------------------------
	  * 修改
	  */
	function updateCustomer( customerId ){
     //   address();  // 加载地址信息
         resetCustomer();		// 清空表单数据

		$.ajax({
	  		url :"/customer/getCustomer.do",
	  		data:"customerId="+customerId ,
			type:"POST",
			dataType : "json",
			success : function(data) {

				if(data.flag==true){
                  //  firstName  lastName  address   email

					var customer =data.data;
					  $('#firstName').val(customer.firstName) ;
					  $('#lastName').val(customer.lastName);
					  $('#email').val(customer.email);
 					  $('#customerId').val(customer.customerId) ;
					  $('#address').val(customer.addressId) ;

				} else {
		      		layer.msg('查询失败！', {icon: 5});	
				}
			},
			error : function() {
	      		layer.msg('加载失败！', {icon: 5});

				return ;
			}
		});    // ajax 结束


		$('#customerWin').removeClass("hide");    //  弹框显示

		  var index =  layer.open({       
				type : 1,
				shade : 0.4,
				title : '修改Customer',
				offset:'50px',
		        maxmin: true,
		        shadeClose: true, //点击遮罩关闭层
		        area : ['65%' , '75%'],
		 	  	btn: ['修改', '取消'],
		 	    content : $('#customerWin'), //捕获的元素
				yes : function(index, layero) {  
					// 表单提交前需要校验
					var firstName = $('#firstName').val() ;
					var lastName = $('#lastName').val();
					var email = $('#email').val();
					var address = $('#address').val();
					var customerId = $('#customerId').val();

                    if(  $("#firstName").val() == ""||$("#lastName").val() == "" )
                    {
                        layer.msg("firstName或lastName不能为空",  {icon: 3});
                        return false;
                    }

                     if(email != null & email != ""){
 						if(!checkEmail(email)){   	// 邮箱格式验证
                            return false;
						}
					}

					 var customerData = "firstName=" +firstName+"&lastName=" +lastName+"&address="+address+
					 				"&customerId="+customerId+"&email="+email ;

					 console.log("修改："+customerData);

				  	$.ajax({
				  		url :"/customer/updateCustomer.do",
						type:"POST",
                        data:{firstName:firstName,lastName:lastName,addressId:address,email:email,customerId:customerId},
						dataType : "json",
						success : function(data) {
							if(data.flag==true){
								layer.msg("修改完成！", {icon: 1});

								if(pageNum == null){
                                    pageNum=1;
								}
                                initCustomerInfo(pageNum,pageSize,null);     // 重新加载框架
							} else {
					      		layer.msg('修改失败！', {icon: 5});	
							}
						},
						error : function() {
				      		layer.msg('加载失败！', {icon: 5});	

						} 
					});    // ajax 结束
					 
				  	layer.close(index);
					$('#customerWin').addClass("hide"); //取消继续隐藏
				},
				cancel : function(index, layero) {
					layer.close(index);
					$('#customerWin').addClass("hide"); //取消继续隐藏
				} 
			});   // layer 结束
		
	}
	
	
	 /**
	  * -----------------------------------------------
	  * 删除
	  */
	function deleteCustomer( customerId ){

 		layer.confirm('您是确定要删除吗？', {
			  btn: ['确定','取消'] //按钮
			}, function(){
		
				$.ajax({
			  		url :"/customer/deleteCustomer.do",
			  		data:"customerId="+customerId ,
					type:"POST",
					dataType : "json",
					success : function(data) {
						
						if(data.flag==true){
							layer.msg('删除成功！', {icon: 1});
							if(pageNum == null){
								pageNum = 1;
							}
                            initCustomerInfo(pageNum,pageSize,null);     // 重新加载框架
						} else {
				      		layer.msg('删除失败！', {icon: 5});	
						}
					},
					error : function() {
			      		layer.msg('操作失败！', {icon: 5});

					} 
				});    // ajax 结束
					
			
			});    // confirm 结束
		 
			
	} // 删除结束

	/**
	 * ---------------------------------------------------
	 *   加载 地址信息到下拉框
	 */
	function address() {

		$.ajax({
			type:"POST",
			url:contextPath+"/customer/getAddress.do",
			success : function(data) {
				if (data.flag==false) {
					layer.msg('查询失败！', {icon: 5});
					return false;
				}

				if(data.flag==true){
				   var  address = data.data;
					var htmlAddressr ="";
					$.each(address,function(i,o){
                        htmlAddressr+=   " <option value=" +o.addressId +" >"+  o.address+"</option>"
					});
					$("#address").html(htmlAddressr);
				}
			},
			error : function() {
				layer.msg('初始化失败！', {icon: 5});
			}
		});   // ajax结束

	 }

	/**
	 *   清空 表单数据
	 */
	function resetCustomer() {
			 $('#firstName').val(null) ;
			 $('#lastName').val(null);
			 $('#email').val(null);
			 $('#customerId').val(null) ;
			 $('#address').val(null) ;
		 }


	  