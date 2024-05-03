    $(document).ready(function(){
    // Number of items per page
    var itemsPerPage = 10;

    // Hide all items initially
    $(".item").hide();

    // Show the first page of items
    $(".item").slice(0, itemsPerPage).show();

    // Calculate number of pages
    var totalPages = Math.ceil($(".item").length / itemsPerPage);

    // Generate pagination controls
    for (var i = 1; i <= totalPages; i++) {
    $("#pagination").append("<button class='page-btn'>" + i + "</button>");
}

    // Handle click event for pagination buttons
    $(".page-btn").click(function(){
    var pageNum = $(this).text();

    // Hide all items
    $(".item").hide();

    // Show items for the selected page
    var startIndex = (pageNum - 1) * itemsPerPage;
    var endIndex = startIndex + itemsPerPage;
    $(".item").slice(startIndex, endIndex).show();
});
});
