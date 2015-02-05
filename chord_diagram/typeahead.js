
var Dal_professors = new Bloodhound({
  datumTokenizer: function(d) { return Bloodhound.tokenizers.whitespace(d.team); },
  queryTokenizer: Bloodhound.tokenizers.whitespace,
  prefetch: './data/Dal_Professors.json'
    
});

var Stanford_professors = new Bloodhound({
  datumTokenizer: function(d) { return Bloodhound.tokenizers.whitespace(d.team); },
  queryTokenizer: Bloodhound.tokenizers.whitespace,
  prefetch: './data/Stanford_Professors.json'
    
});

 
// initialize the bloodhound suggestion engine
Dal_professors.initialize();
Stanford_professors.initialize();
 
// instantiate the typeahead UI
// $('.example-numbers .typeahead').typeahead(null, {
//   displayKey: 'num',
//   source: numbers.ttAdapter()
// });


$('.typeahead-tt-input').typeahead({
  highlight: true
}, 
{  
    name: 'Dal_professors',
    source: Dal_professors.ttAdapter(),
    displayKey: 'team',
    templates: {
    header: '<h3 class="league-name">Dalhousie</h3>'
    }
},
{  
    name: 'Stanford_professors',
    source: Stanford_professors.ttAdapter(),
    displayKey: 'team',
    templates: {
    header: '<h3 class="league-name">Stanford</h3>'
    }                                                                                                
});

