
  $( document ).ready(function() {
  var r = jsRoutes.shine.st.blog.controllers.Category.count();
  console.log(r);
  console.log(r.type);

 $.ajax({
      url: r.url,
      type:r.type
      }).done(function(categoryHtml) {

      console.log(categoryHtml);
        $("#category").html(categoryHtml);
      }).fail(function( xhr, status, errorThrown ) {
            alert( "Sorry, there was a problem!" );
            console.log( "Error: " + errorThrown );
            console.log( "Status: " + status );
            console.dir( xhr );
          }).always(function( xhr, status ) {
//                console.log("complete");
              });
});

