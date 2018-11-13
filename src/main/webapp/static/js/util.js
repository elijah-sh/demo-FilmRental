
function getFormatDate(time){
    var date=new Date();

    var data = new Date(time);
    var year = data.getFullYear();  //获取年
    var month = data.getMonth() + 1;    //获取月
    var day = data.getDate(); //获取日
    var hours = data.getHours();
    var minutes = data.getMinutes();
    var second = data.getSeconds();
/*    if (date.getFullYear()==year&&date.getMonth() + 1==month){
        if (date.getDate()==day){
            time = "今天" + " " + hours + ":" + minutes;
            return time;
        }
        if ((parseInt(date.getDate())-1)==day){
            time = "昨天" + " " + hours + ":" + minutes;
            return time;
        }
    }*/

    time = year + "-" + month + "-" + day + "" + " " + hours + ":" + minutes+":"+second;
    return time;
}



/*
 **********************************
 * 邮箱验证
 * */
function checkEmail(str){
    var reg = new RegExp("^[A-Za-z0-9]+([-_.][A-Za-z0-9]+)*@([A-Za-z0-9]+[-.])+[A-Za-z0-9]{2,4}$"); //正则表达式

     if(reg.test(str)!= true) {
         layer.msg('邮箱格式不正确！', {icon: 5});
         return false;
    }
     return true;
}