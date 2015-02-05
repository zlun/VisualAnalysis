function Filter_Whole_Matrix(iteration){

	if(window.dataset=='Dalhousie'){
		load_whole_Chord_diagram(Dal_matrix[iteration],iteration);
	}
	if(window.dataset=='Stanford'){
		load_whole_Chord_diagram(Stan_matrix[iteration],iteration);
	}
}

function Filter_Part_Matrix(iteration){

	if(window.dataset=='Dalhousie'){
		load_Chord_diagram(Dal_matrix[iteration],iteration);
	}
	if(window.dataset=='Stanford'){
		load_Chord_diagram(Stan_matrix[iteration],iteration);
	}
}
