$(document).on("click", ".delete", function () {
    var selectedUserId = $(this).data("user");
    $('input[name="userId"]').val( selectedUserId );
});

$(document).on("click", ".edit", function () {
    $('input[name="id"]').val( $(this).data("id") );
    $('input[name="name"]').val( $(this).data("name") );
    $('input[name="email"]').val( $(this).data("email") );
});
