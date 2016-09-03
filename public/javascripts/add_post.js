$(document).ready(function() {
    var r = jsRoutes.shine.st.blog.controllers.CategoriesCtrl.allCategoriesToBackend();

    $.ajax({
                url: r.url,
                type: r.type
            }).done(function(categoryHtml) {
                $("#categories_menu").html(categoryHtml);
                $('.ui.selection.dropdown').dropdown();
            }).fail(function(xhr, status, errorThrown) {
                alert("Sorry, there was a problem!");
                console.log("Error: " + errorThrown);
                console.log("Status: " + status);
                console.dir(xhr);
            }).always(function(xhr, status) {

            });
});


var checkSubmit = false;

$( '#post_form' ).submit(function( event ) {
  var file = $("#file_name")
  if(file.val() == '') {
    alert('please choose upload file');
    event.preventDefault();
    return;
  }

  if(!checkSubmit) {
       var form = $(this);
       event.preventDefault();
       setTimeout(function(){ form.submit() }, 2000);
       console.log('file not prepared');
  }
  });


$('#file_name').change(function( event ) {
    var stream = $(this)[0].files[0];
    var md = window.markdownit();
    var reader = new FileReader();
    reader.onload = function() {
        var data = reader.result;
        var content = md.render(data);
        $('#md_content').val(data);
        $('#html_content').val(content);
        checkSubmit = true;
    };

    reader.readAsText(stream);
});

$('.ui.checkbox').checkbox().first().checkbox({
    onChange: function() {
        var checked = this.checked
        var value = (checked && 1) || 2;
        $('#brief_way').val(value);
        var textarea = $('textarea')
        textarea.attr('disabled',checked);
        textarea.val('');
    }
});

