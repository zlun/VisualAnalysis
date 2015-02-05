$(document).ready(function() {

var numbers = new Bloodhound({
  datumTokenizer: function(d) { return Bloodhound.tokenizers.whitespace(d.num); },
  queryTokenizer: Bloodhound.tokenizers.whitespace,
  local: [
    { num: 'one' },
    { num: 'two' },
    { num: 'three' },
    { num: 'four' },
    { num: 'five' },
    { num: 'six' },
    { num: 'seven' },
    { num: 'eight' },
    { num: 'nine' },
    { num: 'ten' }
  ]
});
 
// initialize the bloodhound suggestion engine
numbers.initialize();
 
// instantiate the typeahead UI
// $('.example-numbers .typeahead').typeahead(null, {
//   displayKey: 'num',
//   source: numbers.ttAdapter()
// });


$('.typeahead-tt-input').typeahead(null, {  
    source: numbers.ttAdapter(),
    display: 'num',
    limit: 10,
    templates: {
    header: '<h3 class="league-name">Dalhousie</h3>'
  }                                                                      
  });



});