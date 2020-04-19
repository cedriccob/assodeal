$(function () {

    function addError(id, e){
        if($(id).val()==='') {
            $(id).css("border", "1px solid red");
            e.preventDefault();
        }
        else{
            $(id).css("border", "none");
        }
    }



    $('#submit').click(function(e){
        $("#form-register input").each(function(){
            addError($(this), e);
        });

    });

});
