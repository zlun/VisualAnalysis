# VisualAnalysis
The visualization of user profile classification and relationship.

## Implementation
* Use html, css and javascript.
* An interative visualization system is implemented by D3.js javascript library that helps users to explore data easily.

## Content
* In chord_diagram folder
	* chord_diagram.html: the main page that contains filters and control buttons for users to visualize the common interest relationships

	* choosedataset.js: we classify the professors based on their general tags. For each professor, we have predicted classes and their actual ones. The ChooseDataset function is used for choosing desired dataset and the ChooseClass function is used for demonstrating the actual class of the professor.

	* AddProf.js: there are many professors in the dataset, we use filter to select the user interested professors to show their relationships. This function is used for showing the name of selected professors.
	
	* load_chord_diagram.js: function load_Chord_diagram (matrix,iter) shows the chord diagram of selected professors in different iterations. 

	* load_whole_chord_diagram.js: function load_whole_Chord_diagram(matrix,iter) shows the whole chord diagram of professors in different iterations.
	
	* FilterMatrix.js: defined functions to call functions load_chord_diagram(matrix,iter) and load_whole_Chord_diagram(matrix,iter)

* In classification folder:
	* chart.html: the main page that shows the classification accuracy for different datasets. 

	* chart.js: an interactive bar chart visualizes the classification accuracies.

* In WordsCloud folder:
	* index.html: a simple example of word cloud for extracted key phrases in computer science department of Dalhousie University.

## Installztion
Make sure you have already installed Python.

## How to run? 
To run the web page, using python to set up a local server is recommended.

In terminal, you should go to the directory which contains this README.

Type: python -m SimpleHTTPServer 8080 & 

You can access the web page in localhost:8080

You can visit https://web.cs.dal.ca/~zwu/systemdemo/

The demo video is available to download at https://web.cs.dal.ca/~zwu/HonorThesis/demo.mp4

