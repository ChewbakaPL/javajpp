//============== START APPLICATION ==============
var app = {
	notes: null,
	$noteContent: null,
	$noteName: null,
	$selectNote: null,
	
	//a renommer ? (voir norme object Javascript)
	constructor: function(){
		this.notes = [],
		this.$noteContent = $("#txt-note-content");
		this.$noteName = $("#txt-note-name");
		this.$selectNote = $("#select-notes");
	},
	
	showAjaxMessage: function(data, isError){
		var messageType = "info";
		
		//For an error message with a non-2001 HTTP status code
		//the content will not be parsed by jQuery...
		if(typeof isError != 'undefined' && isError){
			data = $.parseJSON(data.responseText);
			messageType = "error";
		}
		
		//Success if 200 or 204
		if(data.code==200 || data.code==204){
			messageType = "success";
		}
		
		switch (messageType) {
		  case "success":
			  toastr.success(":)", data.message);
			  break;
		  case "error":
			  toastr.error(":(",  data.message);
			  break;
		  default:
			  toastr.info("",  data.message);
		}
	},
	
	deleteCurrentNote: function(){
		var self = this;
	    var id = this.$selectNote.val();
	    if(id) {
	        $.ajax({
	            url: './api/notes/' + id,
	            type: 'DELETE', // Methode HTTP DELETE !
	            dataType: 'json',
	            contentType:"application/json; charset=utf-8",
	            success: function() {
	            	self.loadNotes();
	            }, 
	            error: function(error) {
	            	self.showAjaxMessage(error, true);
	            }
	          });
	    }else{
	    	self.showAjaxMessage({code:400, message:'id non envoyé'}, true);
	    }
	},
	
	saveCurrentNote: function(){
		var self = this;
		var note = {
			id: this.$selectNote.val(),	//avec l'id pour update
			name: this.$noteName.val(),
			content: this.$noteContent.val(),
		};
		$.ajax({
            url: './api/notes',
            type: 'POST', //Methode
            dataType: 'json',
            contentType: "application/json; charset=utf-8", // JSON + UTF8
            data: JSON.stringify(note), // Contenu de la requete HTTP, Objet JS serialisé
            success: function(note) {
		    	//self.notes.push(note);
		    	//self.showSelect();
            	
		    	self.loadNotes();  //PB NE RESTE PAS SUR LA NOTE SELECTIONNE
            }, 
            error: function(error) {
            	self.showAjaxMessage(error, true);
            }
        });
		
	},
	
	showSelectedNote: function(){
		var id = this.$selectNote.val();
		var self = this;
		$.ajax({
		    url: './api/notes/'+id, // URL relative
		    type: 'GET', // Methode HTTP
		    dataType: 'json', // Retourne du JSON
		    contentType:"application/json; charset=utf-8",
		    success: function(note) {
		    	self.showNote(note);
		    }, 
		    error: function(error) {
		    	self.showAjaxMessage(error, true)
		    }
		  });
	},
	
	createNote: function(){
		var self = this;
		var note = {
			name: this.$noteName.val(),
			content: this.$noteContent.val(),
		};
			  
		$.ajax({
            url: './api/notes',
            type: 'POST', //Methode
            dataType: 'json',
            contentType: 'application/json; charset=utf-8', // JSON + UTF8
            data: JSON.stringify(note), // Contenu de la requete HTTP, Objet JS serialisé
            success: function(note) {
		    	self.notes.push(note);
		    	self.showSelect();
            }, 
            error: function(error) {
            	self.showAjaxMessage(error, true);
            }
        });
	},
	
	loadNotes: function(){
		var self = this;
		$.ajax({
		    url: './api/notes', // URL relative
		    type: 'GET', // Methode HTTP
		    dataType: 'json', // Retourne du JSON
		    contentType:"application/json; charset=utf-8",
		    success: function(list) {
		    	self.notes = list;
		    	self.showSelect();
		    }, 
		    error: function(error) {
		    	self.showAjaxMessage(error, true)
		    }
		  });
	},
	
	//fonctions mise a jour de la vue:
	showSelect: function(){
		var self = this;
		
    	//select: reset
		self.$selectNote.html('');
		
    	//select: add options
		self.notes.forEach(note => {
        	self.$selectNote.append(new Option(note.name, note.id));
	    });
	},
	
	
	showNote: function(noteObj){
		this.$noteName.val(noteObj.name);
		this.$noteContent.val(noteObj.content);
		this.$selectNote.val(noteObj.id);
	}
	
};
//============== END APPLICATION ==============


//============== START DOCUMENT READY ==============
$(document).ready(function(){
	
	console.log("JQUERY DOC READY");
	app.constructor();
	app.loadNotes();
	
})
//============== END DOCUMENT READY ==============
