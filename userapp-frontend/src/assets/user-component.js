/*Delete modal form*/
$(document).on("click", ".delete", function () {
    var selectedUserId = $(this).data("user");
    $('input[name="userId"]').val(selectedUserId);
});

/*Update modal form*/
$(document).on("click", ".edit", function () {
    $('input[name="id"]').val($(this).data("id"));
    $('input[name="name"]').val($(this).data("name"));
    $('input[name="email"]').val($(this).data("email"));
    $('img[name="photo"]').attr("src", $(this).data("photo"));
});

/*Avatar upload*/
$(document).ready(function () {
    var readURL = function (input) {
        if (input.files && input.files[0]) {
            if (input.files[0].size / 1024 / 1024 > 1) {
                alert('File size exceeds 1 MB');
            } else {
                var reader = new FileReader();
                reader.onload = function (e) {
                    $('.profile-pic').attr('src', e.target.result);
                }
                reader.readAsDataURL(input.files[0]);
            }
        }
    }
    $(".file-upload").on('change', function () {
        readURL(this);
    });
    $(".upload-button").on('click', function () {
        $(".file-upload").click();
    });
});
