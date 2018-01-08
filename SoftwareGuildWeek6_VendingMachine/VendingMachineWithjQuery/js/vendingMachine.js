$(document).ready(function () {
  loadSnacks();


  var cash = $('#increment').text();
  //var changeString = "";

  $('#addDollar').click(function(){
    //var cash = $('#increment').text();
    cash ++;
    $('#increment').text(cash.toFixed(2));
    });

  $('#addQuarter').click(function(){
    //var cash = $('#increment').text();
    cash = (cash*100 + 25)/100;
    $('#increment').text(cash.toFixed(2));
    });

  $('#addDime').click(function(){
    //var cash = $('#increment').text();
    cash = (cash*100 + 10)/100;
    $('#increment').text(cash.toFixed(2));
    });

  $('#addNickel').click(function(){
    //var cash = $('#increment').text();
    cash = (cash*100 + 5)/100;
    $('#increment').text(cash.toFixed(2));
    });

  $('#makePurchase').click(function(event){
      var choice = $('#choiceNumber').contents().text();
      $.ajax({
         type: 'GET',
         url: 'http://localhost:8080/money/' + cash + "/item/" + choice,

         success: function(result) {
              var changeString = '';
              changeString += 'Quarters: ' + result.quarters + ' ';
              changeString += 'Dimes: ' + result.dimes + ' ';
              changeString += 'Nickels: ' + result.nickels + ' ';
              cash = 0;
              $('#change').html("");
              $('#change').text(changeString);
              $('#displayInfo').text("Thank You!");
              $('#increment').text(cash.toFixed(2));
              loadSnacks();
         },
         error: function(result) {
              $('#displayInfo').empty();
              $('#displayInfo').append(result.responseJSON.message);
         }

     });

  });

  $('#changeReturn').click(function(){
    var quarters = 0;
    var dimes = 0;
    var nickels = 0;
    var amt;
    var changeToReturn = '';

    var change = $('#increment').contents().text();

    if (change != 0.00){
      amt = change * 100;
      quarters = Math.floor(amt/25);
      amt %= 25;
      dimes = Math.floor(amt/10);
      amt %= 10;
      nickels = Math.floor(amt/5);

      changeToReturn += "Quarters: " + quarters + " ";
      changeToReturn += "Dimes: " + dimes + " ";
      changeToReturn += "Nickels: " + nickels + " ";

      $('#change').text(changeToReturn);


    } else {
      $('#change').empty();

    }
    cash = 0;
    $('#increment').text(cash.toFixed(2));
    change = 0;
  });


});
function loadSnacks() {
    // we need to clear the previous content so we don't append to it
    clearSnacksTable();

    // grab the the tbody element that will hold the rows of contact information
    var snacksTable = $('#snacksTable');
    console.log('before ajax')

    $.ajax ({
        type: 'GET', //parameter 1,
        url: 'http://localhost:8080/items',//parameter 2,we are running on localhost, the path is contacts
        success: function (data) {//parameter 3, if we want to process things, what to return from Ajax call, we will take in contactArray
            //console.log('after ajax before each')

            $.each(data, function (index, item) {//was contactArray for first parameter, second paraemeter is a function,
                var id = item.id;
                var name = item.name;
                var price = item.price;
                var quantity = item.quantity;

                console.log('item printed' + id);

                var row = '<button type="button" class="col-md-4 snackButton">';//generate HTML
                    row += '<p class= "text-left index" >' + id + '<p>'
                    row += '<h4>' + name + '</h4>';//append each table cell
                    row += '<p>' + price + '$ </p>';
                    row += '<hr>';
                    row += '<p>Quantity Left:' + quantity + '</p>';
                    row += '</button>';

                $('#snacksTable').append(row);//add class to each button, incorrect, not add class to the whole table


            });

            $('.snackButton').click(function (event){
              choice = $(this).find('.index').text();
              console.log('choice index' + choice);
              $('#choiceNumber').text(choice);
            });
        },
        error: function(xhr) {//parameter 4,
            //console.log(xhr.status + 'after ajax before errorMessages')
            $('#errorMessages')//grab reference with jQuery from HTML, on line 13,
                .append($('<li>')//append list item
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service.  Please try again later.'));
        }
    });

}
function clearSnacksTable() {
    $('#snacksTable').empty();
}
