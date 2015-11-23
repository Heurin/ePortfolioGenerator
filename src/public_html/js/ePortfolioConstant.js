/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 
 var pages,contents;
var json;
var title;
var subtitle;
var slides;
var current;

$(document).ready(function() {	
	$.getJSON("json/pagedata.json",function(data){
        json = data;
		var sd = $('#titletext').text();
		var pagenum = parseInt(sd,10);
		if(pagenum ==1)
			$('#l1').html("<u><bold>Welcome</bold></u>");
		if(pagenum ==2)
			$('#l2').html("<u><bold>About me</bold></u>");
		if(pagenum ==3)
			$('#l3').html("<u><bold>Skills</bold></u>");
		if(pagenum ==4)
			$('#l4').html("<u><bold>Gallery</bold></u>");
		if(pagenum ==5)
			$('#l5').html("<u><bold>ETC</bold></u>");
        title = data.title;
		
        subtitle = json.pages[pagenum-1].subtitle;
        $('#titletext').text("ePortfolio || " + title + " || " +  subtitle);
        contents = json.pages[pagenum-1].components.length;
	$('.content').empty();
        $.each(json.pages[pagenum-1].components, function(key, val){
			if(val.type === "text")
				$('.content').append("<div class='text'>" + val.content + "</div><br>");
			if(val.type === "image"){
				$('.content').append("<img class='image' src=" + val.image_path + val.image_file_name + "><br>");
			}
			if(val.type === "video"){
				$('.content').append("<video class='video' controls> <source src=" + val.video_path + val.video_file_name + " type=video/mp4 ></video><br>");
			}
			if(val.type === "slideshow"){
				$('.content').append("<img id='SlideImage' ><br><p id='captionparagraph'></p><div class='slideshowbuttons'><img id='PrevButton' class='button' src='data/icons/prevbutton.png'></img>");
				$('.content').append("<img id='PlayButton' class='button' src='data/icons/playbutton.png'><img>");
				$('.content').append("<img id='NextButton' src='data/icons/nextbutton.png' class='button'></img></div>");

				
				$('#SlideImage').attr('src',val.slides[0].image_path + val.slides[0].image_file_name);
				$('#captionparagraph').text(val.slides[0].caption);
				slides = 0;
				slides = val.slides.length;
				current = 0;
				$("#PrevButton").click(function (){
                if(current ===0){
                    $('#SlideImage').attr('src',val.slides[slides-1].image_path + val.slides[slides-1].image_file_name);
                    $('#captionparagraph').text(val.slides[slides-1].caption);
                    current = slides;
                }
                else {
                    $('#SlideImage').attr('src',val.slides[current-1].image_path + val.slides[current-1].image_file_name);
                    $('#captionparagraph').text(val.slides[current-1].caption);
                    current --;
                }
				});
				$("#NextButton").click(function () {
                if(current ===slides-1){
                    $('#SlideImage').attr('src',val.slides[0].image_path + val.slides[0].image_file_name);
                    $('#captionparagraph').text(val.slides[0].caption);
                    current = 0;
                }
                else {
                    $('#SlideImage').attr('src',val.slides[current+1].image_path + val.slides[current+1].image_file_name);
                    $('#captionparagraph').text(val.slides[current+1].caption);
                    current ++;
                }                       
				});
				play = false;
				$("#PlayButton").click(function () {
                if (play) {
                    $("#PlayButton").attr('src',"data/icons/playbutton.png");
                    clearInterval(interval);
                    play = false;
                }
                else {
                    $("#PlayButton").attr('src',"data/icons/stopbutton.png");
                    play = true;
                    interval = setInterval(function() {
                                    if(current ===slides-1){
                                        $('#SlideImage').attr('src',val.slides[0].image_path + val.slides[0].image_file_name);
                                        $('#captionparagraph').text(val.slides[0].caption);
                                        current = 0;
                                    }
                                    else {
                                        $('#SlideImage').attr('src',val.slides[current+1].image_path + val.slides[current+1].image_file_name);
                                        $('#captionparagraph').text(val.slides[current+1].caption);
                                        current ++;
                                }                   
                                },1000);;                    

                }        
            });

			}
			
		});
	/**	
	$("#li").text("<bold>Welcome</bold>"); 	
    });
    $("#l1").click(function() {
        subtitle = json.pages[0].subtitle;
        $('#titletext').text("ePortfolio || " + title + " || " +  subtitle);
		$('.content').empty();
        $.each(json.pages[0].components, function(key, val){
			if(val.type === "text")
				$('.content').append("<div class='text'>" + val.content + "</div><br>");
			if(val.type === "image"){
				$('.content').append("<img class='image' src=" + val.image_path + val.image_file_name + "><br>");
			}
			if(val.type === "video"){
				$('.content').append("<video class='video' controls> <source src=" + val.video_path + val.video_file_name + " type=video/mp4 ></video><br>");
			}
			if(val.type === "slideshow"){
				$('.content').append("<img id='SlideImage' ><br><p id='captionparagraph'></p><div class='slideshowbuttons'><img id='PrevButton' class='button' src='data/icons/prevbutton.png'></img>");
				$('.content').append("<img id='PlayButton' class='button' src='data/icons/playbutton.png'><img>");
				$('.content').append("<img id='NextButton' src='data/icons/nextbutton.png' class='button'></img></div>");

				
				$('#SlideImage').attr('src',val.slides[0].image_path + val.slides[0].image_file_name);
				$('#captionparagraph').text(val.slides[0].caption);
				slides = 0;
				slides = val.slides.length;
				current = 0;
				$("#PrevButton").click(function (){
                if(current ===0){
                    $('#SlideImage').attr('src',val.slides[slides-1].image_path + val.slides[slides-1].image_file_name);
                    $('#captionparagraph').text(val.slides[slides-1].caption);
                    current = slides;
                }
                else {
                    $('#SlideImage').attr('src',val.slides[current-1].image_path + val.slides[current-1].image_file_name);
                    $('#captionparagraph').text(val.slides[current-1].caption);
                    current --;
                }
				});
				$("#NextButton").click(function () {
                if(current ===slides-1){
                    $('#SlideImage').attr('src',val.slides[0].image_path + val.slides[0].image_file_name);
                    $('#captionparagraph').text(val.slides[0].caption);
                    current = 0;
                }
                else {
                    $('#SlideImage').attr('src',val.slides[current+1].image_path + val.slides[current+1].image_file_name);
                    $('#captionparagraph').text(val.slides[current+1].caption);
                    current ++;
                }                       
				});
				play = false;
				$("#PlayButton").click(function () {
                if (play) {
                    $("#PlayButton").attr('src',"data/icons/playbutton.png");
                    clearInterval(interval);
                    play = false;
                }
                else {
                    $("#PlayButton").attr('src',"data/icons/stopbutton.png");
                    play = true;
                    interval = setInterval(function() {
                                    if(current ===slides-1){
                                        $('#SlideImage').attr('src',val.slides[0].image_path + val.slides[0].image_file_name);
                                        $('#captionparagraph').text(val.slides[0].caption);
                                        current = 0;
                                    }
                                    else {
                                        $('#SlideImage').attr('src',val.slides[current+1].image_path + val.slides[current+1].image_file_name);
                                        $('#captionparagraph').text(val.slides[current+1].caption);
                                        current ++;
                                }                   
                                },1000);;                    

                }        
            });

			}
			
		});    
    });
    $("#l2").click(function() {
        subtitle = json.pages[1].subtitle;
        $('#titletext').text("ePortfolio || " + title + " || " +  subtitle);
        $('.content').empty();
        $.each(json.pages[1].components, function(key, val){
			if(val.type === "text")
				$('.content').append("<div class='text'>" + val.content + "</div><br>");
			if(val.type === "image"){
				$('.content').append("<img class='image' src=" + val.image_path + val.image_file_name + "><br>");
			}
			if(val.type === "video"){
				$('.content').append("<video class='video' controls> <source src=" + val.video_path + val.video_file_name + " type=video/mp4 ></video><br>");
			}
			if(val.type === "slideshow"){
				$('.content').append("<img id='SlideImage' ><br><p id='captionparagraph'></p><div class='slideshowbuttons'><img id='PrevButton' class='button' src='data/icons/prevbutton.png'></img>");
				$('.content').append("<img id='PlayButton' class='button' src='data/icons/playbutton.png'><img>");
				$('.content').append("<img id='NextButton' src='data/icons/nextbutton.png' class='button'></img></div>");

				
				$('#SlideImage').attr('src',val.slides[0].image_path + val.slides[0].image_file_name);
				$('#captionparagraph').text(val.slides[0].caption);
				slides = 0;
				slides = val.slides.length;
				current = 0;
				$("#PrevButton").click(function (){
                if(current ===0){
                    $('#SlideImage').attr('src',val.slides[slides-1].image_path + val.slides[slides-1].image_file_name);
                    $('#captionparagraph').text(val.slides[slides-1].caption);
                    current = slides;
                }
                else {
                    $('#SlideImage').attr('src',val.slides[current-1].image_path + val.slides[current-1].image_file_name);
                    $('#captionparagraph').text(val.slides[current-1].caption);
                    current --;
                }
				});
				$("#NextButton").click(function () {
                if(current ===slides-1){
                    $('#SlideImage').attr('src',val.slides[0].image_path + val.slides[0].image_file_name);
                    $('#captionparagraph').text(val.slides[0].caption);
                    current = 0;
                }
                else {
                    $('#SlideImage').attr('src',val.slides[current+1].image_path + val.slides[current+1].image_file_name);
                    $('#captionparagraph').text(val.slides[current+1].caption);
                    current ++;
                }                       
				});
				play = false;
				$("#PlayButton").click(function () {
                if (play) {
                    $("#PlayButton").attr('src',"data/icons/playbutton.png");
                    clearInterval(interval);
                    play = false;
                }
                else {
                    $("#PlayButton").attr('src',"data/icons/stopbutton.png");
                    play = true;
                    interval = setInterval(function() {
                                    if(current ===slides-1){
                                        $('#SlideImage').attr('src',val.slides[0].image_path + val.slides[0].image_file_name);
                                        $('#captionparagraph').text(val.slides[0].caption);
                                        current = 0;
                                    }
                                    else {
                                        $('#SlideImage').attr('src',val.slides[current+1].image_path + val.slides[current+1].image_file_name);
                                        $('#captionparagraph').text(val.slides[current+1].caption);
                                        current ++;
                                }                   
                                },1000);;                    

                }        
            });

			}
			
		});            
    });   
    $("#l3").click(function() {
        subtitle = json.pages[2].subtitle;
        $('#titletext').text("ePortfolio || " + title + " || " +  subtitle);
	$('.content').empty();
        $.each(json.pages[2].components, function(key, val){
			if(val.type === "text")
				$('.content').append("<div class='text'>" + val.content + "</div><br>");
			if(val.type === "image"){
				$('.content').append("<img class='image' src=" + val.image_path + val.image_file_name + "><br>");
			}
			if(val.type === "video"){
				$('.content').append("<video class='video' controls> <source src=" + val.video_path + val.video_file_name + " type=video/mp4 ></video><br>");
			}
			if(val.type === "slideshow"){
				$('.content').append("<img id='SlideImage' ><br><p id='captionparagraph'></p><div class='slideshowbuttons'><img id='PrevButton' class='button' src='data/icons/prevbutton.png'></img>");
				$('.content').append("<img id='PlayButton' class='button' src='data/icons/playbutton.png'><img>");
				$('.content').append("<img id='NextButton' src='data/icons/nextbutton.png' class='button'></img></div>");

				
				$('#SlideImage').attr('src',val.slides[0].image_path + val.slides[0].image_file_name);
				$('#captionparagraph').text(val.slides[0].caption);
				slides = 0;
				slides = val.slides.length;
				current = 0;
				$("#PrevButton").click(function (){
                if(current ===0){
                    $('#SlideImage').attr('src',val.slides[slides-1].image_path + val.slides[slides-1].image_file_name);
                    $('#captionparagraph').text(val.slides[slides-1].caption);
                    current = slides;
                }
                else {
                    $('#SlideImage').attr('src',val.slides[current-1].image_path + val.slides[current-1].image_file_name);
                    $('#captionparagraph').text(val.slides[current-1].caption);
                    current --;
                }
				});
				$("#NextButton").click(function () {
                if(current ===slides-1){
                    $('#SlideImage').attr('src',val.slides[0].image_path + val.slides[0].image_file_name);
                    $('#captionparagraph').text(val.slides[0].caption);
                    current = 0;
                }
                else {
                    $('#SlideImage').attr('src',val.slides[current+1].image_path + val.slides[current+1].image_file_name);
                    $('#captionparagraph').text(val.slides[current+1].caption);
                    current ++;
                }                       
				});
				play = false;
				$("#PlayButton").click(function () {
                if (play) {
                    $("#PlayButton").attr('src',"data/icons/playbutton.png");
                    clearInterval(interval);
                    play = false;
                }
                else {
                    $("#PlayButton").attr('src',"data/icons/stopbutton.png");
                    play = true;
                    interval = setInterval(function() {
                                    if(current ===slides-1){
                                        $('#SlideImage').attr('src',val.slides[0].image_path + val.slides[0].image_file_name);
                                        $('#captionparagraph').text(val.slides[0].caption);
                                        current = 0;
                                    }
                                    else {
                                        $('#SlideImage').attr('src',val.slides[current+1].image_path + val.slides[current+1].image_file_name);
                                        $('#captionparagraph').text(val.slides[current+1].caption);
                                        current ++;
                                }                   
                                },1000);;                    

                }        
            });

			}
			
		});    
    });
    $("#l4").click(function() {
        subtitle = json.pages[3].subtitle;
        $('#titletext').text("ePortfolio || " + title + " || " +  subtitle);
        $('.content').empty();
        $.each(json.pages[3].components, function(key, val){
			if(val.type === "text")
				$('.content').append("<div class='text'>" + val.content + "</div><br>");
			if(val.type === "image"){
				$('.content').append("<img class='image' src=" + val.image_path + val.image_file_name + "><br>");
			}
			if(val.type === "video"){
				$('.content').append("<video class='video' controls> <source src=" + val.video_path + val.video_file_name + " type=video/mp4 ></video><br>");
			}
			if(val.type === "slideshow"){
				$('.content').append("<img id='SlideImage' ><br><p id='captionparagraph'></p><div class='slideshowbuttons'><img id='PrevButton' class='button' src='data/icons/prevbutton.png'></img>");
				$('.content').append("<img id='PlayButton' class='button' src='data/icons/playbutton.png'><img>");
				$('.content').append("<img id='NextButton' src='data/icons/nextbutton.png' class='button'></img></div>");

				
				$('#SlideImage').attr('src',val.slides[0].image_path + val.slides[0].image_file_name);
				$('#captionparagraph').text(val.slides[0].caption);
				slides = 0;
				slides = val.slides.length;
				current = 0;
				$("#PrevButton").click(function (){
                if(current ===0){
                    $('#SlideImage').attr('src',val.slides[slides-1].image_path + val.slides[slides-1].image_file_name);
                    $('#captionparagraph').text(val.slides[slides-1].caption);
                    current = slides;
                }
                else {
                    $('#SlideImage').attr('src',val.slides[current-1].image_path + val.slides[current-1].image_file_name);
                    $('#captionparagraph').text(val.slides[current-1].caption);
                    current --;
                }
				});
				$("#NextButton").click(function () {
                if(current ===slides-1){
                    $('#SlideImage').attr('src',val.slides[0].image_path + val.slides[0].image_file_name);
                    $('#captionparagraph').text(val.slides[0].caption);
                    current = 0;
                }
                else {
                    $('#SlideImage').attr('src',val.slides[current+1].image_path + val.slides[current+1].image_file_name);
                    $('#captionparagraph').text(val.slides[current+1].caption);
                    current ++;
                }                       
				});
				play = false;
				$("#PlayButton").click(function () {
                if (play) {
                    $("#PlayButton").attr('src',"data/icons/playbutton.png");
                    clearInterval(interval);
                    play = false;
                }
                else {
                    $("#PlayButton").attr('src',"data/icons/stopbutton.png");
                    play = true;
                    interval = setInterval(function() {
                                    if(current ===slides-1){
                                        $('#SlideImage').attr('src',val.slides[0].image_path + val.slides[0].image_file_name);
                                        $('#captionparagraph').text(val.slides[0].caption);
                                        current = 0;
                                    }
                                    else {
                                        $('#SlideImage').attr('src',val.slides[current+1].image_path + val.slides[current+1].image_file_name);
                                        $('#captionparagraph').text(val.slides[current+1].caption);
                                        current ++;
                                }                   
                                },1000);;                    

                }        
            });

			}
			
		});    
    });
    $("#l5").click(function() {
        subtitle = json.pages[4].subtitle;
        $('#titletext').text("ePortfolio || " + title + " || " +  subtitle);
    $('.content').empty();
        $.each(json.pages[4].components, function(key, val){
			if(val.type === "text")
				$('.content').append("<div class='text'>" + val.content + "</div><br>");
			if(val.type === "image"){
				$('.content').append("<img class='image' src=" + val.image_path + val.image_file_name + "><br>");
			}
			if(val.type === "video"){
				$('.content').append("<video class='video' controls> <source src=" + val.video_path + val.video_file_name + " type=video/mp4 ></video><br>");
			}
			if(val.type === "slideshow"){
				$('.content').append("<img id='SlideImage' ><br><p id='captionparagraph'></p><div class='slideshowbuttons'><img id='PrevButton' class='button' src='data/icons/prevbutton.png'></img>");
				$('.content').append("<img id='PlayButton' class='button' src='data/icons/playbutton.png'><img>");
				$('.content').append("<img id='NextButton' src='data/icons/nextbutton.png' class='button'></img></div>");

				
				$('#SlideImage').attr('src',val.slides[0].image_path + val.slides[0].image_file_name);
				$('#captionparagraph').text(val.slides[0].caption);
				slides = 0;
				slides = val.slides.length;
				current = 0;
				$("#PrevButton").click(function (){
                if(current ===0){
                    $('#SlideImage').attr('src',val.slides[slides-1].image_path + val.slides[slides-1].image_file_name);
                    $('#captionparagraph').text(val.slides[slides-1].caption);
                    current = slides;
                }
                else {
                    $('#SlideImage').attr('src',val.slides[current-1].image_path + val.slides[current-1].image_file_name);
                    $('#captionparagraph').text(val.slides[current-1].caption);
                    current --;
                }
				});
				$("#NextButton").click(function () {
                if(current ===slides-1){
                    $('#SlideImage').attr('src',val.slides[0].image_path + val.slides[0].image_file_name);
                    $('#captionparagraph').text(val.slides[0].caption);
                    current = 0;
                }
                else {
                    $('#SlideImage').attr('src',val.slides[current+1].image_path + val.slides[current+1].image_file_name);
                    $('#captionparagraph').text(val.slides[current+1].caption);
                    current ++;
                }                       
				});
				play = false;
				$("#PlayButton").click(function () {
                if (play) {
                    $("#PlayButton").attr('src',"data/icons/playbutton.png");
                    clearInterval(interval);
                    play = false;
                }
                else {
                    $("#PlayButton").attr('src',"data/icons/stopbutton.png");
                    play = true;
                    interval = setInterval(function() {
                                    if(current ===slides-1){
                                        $('#SlideImage').attr('src',val.slides[0].image_path + val.slides[0].image_file_name);
                                        $('#captionparagraph').text(val.slides[0].caption);
                                        current = 0;
                                    }
                                    else {
                                        $('#SlideImage').attr('src',val.slides[current+1].image_path + val.slides[current+1].image_file_name);
                                        $('#captionparagraph').text(val.slides[current+1].caption);
                                        current ++;
                                }                   
                                },1000);;                    

                }        
            });

			}
			
		});
**/		
    });

});

var offset = 300;
var navigationContainer = $('#cd-nav'),
	mainNavigation = navigationContainer.find('#cd-main-nav ul');
	
$(window).scroll(function(){
	checkMenu();
});
 
function checkMenu() {
 
	if( $(window).scrollTop() > offset && !navigationContainer.hasClass('is-fixed')) {
		//add .is-fixed class to #cd-nav 
		//wait for the animation to end  
		//add the .has-transitions class to main navigation (used to bring back transitions)
	} else if ($(window).scrollTop() <= offset) {
		
		//check if the menu is open when scrolling up - for browser that supports transition
		if( mainNavigation.hasClass('is-visible')  && !$('html').hasClass('no-csstransitions') ) {
			//close the menu 
			//wait for the transition to end 
			//remove the .is-fixed class from the #cd-nav and the .has-transitions class from main navigation
		} 
 
		//check if the menu is open when scrolling up - fallback if transitions are not supported
		else if( mainNavigation.hasClass('is-visible')  && $('html').hasClass('no-csstransitions') ) {
			//no need to wait for the end of transition - close the menu and remove the .is-fixed class from the #cd-nav
		} 
 
		//scrolling up with menu closed
		else {
			//remove the .is-fixed class from the #cd-nav and the .has-transitions class from main navigation
		}
	} 
}

