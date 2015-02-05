The visualization of user profile classification and relationship uses the technologies of html, css and javascript.

We uses D3.js javascript library to implement an interactive visualization system that helps user to explore data easily

In chord_diagram folder
	chord_diagram.html: the main page that contains filter and control buttons for users to visualize the common interest relationships

	choosedataset.js: In our expriment, we classify the professors based on their general tags. So for each professor, we have classified class and their actual class. The ChooseDataset function is choosing desired dataset to explore and the ChooseClass function is showing the actual class of the professor.

	AddProf.js: Since there is a big amount of professors in the whole dataset, we use filter to select the professors user interested to show their relationships. This function is showing the name of selected professors.
	
	load_chord_diagram.js: function load_Chord_diagram(matrix,iter) shows the chord diagram of selected professors in different iterations 

	load_whole_chord_diagram.js: function load_whole_Chord_diagram(matrix,iter) shows the whole chord diagram of professors in different iterations
	
	FilterMatrix.js: defines functions to call functions load_chord_diagram(matrix,iter) and load_whole_Chord_diagram(matrix,iter)

In classification folder:
	chart.html: the main page that shows the classification accuracy for different datasets and training sets. 

	chart.js: An interactive bar chart visualize the classification accuracies.

In WordsCloud folder:
	index.html: a simple example of word cloud for extracted key phrases in computer science department of Dalhousie University.

To run the web page, we should set up a local server which I recommend use python to set up a local server.

Make sure you have already installed Python.

In terminal, you should go to the directory which contains this README.
	type: python -m SimpleHTTPServer 8080 & 

So you can access the web page in localhost:8080

Or you can visit https://web.cs.dal.ca/~zwu/systemdemo/

