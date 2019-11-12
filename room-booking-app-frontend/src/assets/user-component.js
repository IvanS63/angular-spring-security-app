$(document).on("click", ".delete", function () {
    console.log($(this));
    var selectedUserId = $(this).data("user");
    $('input[name="userId"]').val( selectedUserId );
    // As pointed out in comments, 
    // it is unnecessary to have to manually call the modal.
    // $('#addBookDialog').modal('show');
});