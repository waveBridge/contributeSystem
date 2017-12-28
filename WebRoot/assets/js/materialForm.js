var label;
var target;
var iconSize = 'fa-2x'; //set iconSize

function Form(){
}

Form.prototype.alterForm= function(){
    
    $('input[class=formInput]').focus(function(e){
        form.setLabel(e.target);
        form.checkFocused();
    });
    $('input[class=formInput]').focusout(function(e){
        form.setLabel(e.target);
        form.checkUnfocused(e.target);
    });
};

Form.prototype.setLabel = function(target){
    label= $('label[for='+target.id+']');
};

Form.prototype.getLabel = function(){
    return label;
};

Form.prototype.checkFocused= function(){
    form.getLabel().addClass('active','');
};

Form.prototype.checkUnfocused= function(target){
    if($(target).val().length == 0){
        form.getLabel().removeClass('active');
    }
};

form = new Form();

function initialize(){
    form.alterForm();
}
initialize();