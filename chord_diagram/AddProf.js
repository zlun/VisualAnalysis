function myFunction()
{ 
  var input_name="";
 
  input_name = $('#input').val();
   
  if(input_name!=""){
  
  $('#mySelect').append('<li class="list-group-item" style="position: relative; vertical-align: top; background-color: transparent;">'+input_name+' </li>');
  
  }
}
            
