$(document).ready(function () {
    $('.filter-button').click(function () {
        var filterValue = $(this).attr('data-filter');
        if (filterValue == 'all') {
            $('.gallery-item').show();
        } else {
            $('.gallery-item').not('.' + filterValue).hide();
            $('.gallery-item.' + filterValue).show();
        }
    });
});
