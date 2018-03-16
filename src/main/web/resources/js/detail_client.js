var contractId = 0;

function ajaxMethod(inputText, contractId) {
    $.ajax({
        url: 'changetariff',
        type: 'GET',
        dataType: 'json',
        contentType: 'application/json',
        mimeType: 'application/json',
        data: ({
            tariffId: inputText,
            contractId: contractId
        }),
        success: function (data) {
            console.log(data);
            var s = '';
            s += '<h4 style="margin-top: 15px;">Options</h4>';
            for (var i = 0; i < data.length; i++) {
                var dis = data[i].disable ? "disabled" : "";
                if (data[i].chacked) {
                    s += '<input name="checkbox"  onclick="checkBoxChange()" id="box_' + data[i].idOption + '" type="checkbox" ' + dis + ' checked value="' + data[i].idOption + '"/>' + data[i].optionEntity.nameOption + '<br>';
                } else {
                    s += '<input name="checkbox" onclick="checkBoxChange()" id="box_' + data[i].idOption + '"  type="checkbox" ' + dis + ' value="' + data[i].idOption + '"/>' + data[i].optionEntity.nameOption + '<br>';
                }
            }
            $("#chekboxes").html('').append(s);
        }
    });
}

function checkBoxChange() {
    var checkValues = $('input[type=checkbox]:checked').map(function () {
        return $(this).val();
    }).get();
    var idContract = $("#idContract").val();
    var idTariff = $('#selector').val();

    console.log(checkValues);

    $.ajax({
        url: 'optionsUser',
        type: 'GET',
        dataType: 'json',
        contentType: 'application/json',
        mimeType: 'application/json',
        data: ({
            tariffId: idTariff,
            contractId: idContract,
            optionList: JSON.stringify(checkValues)
        }),
        success: function (data) {
            console.log(data);
            for (var i = 0; i < data.length; i++) {
                if (data[i].disable) {
                    $("#box_" + data[i].idOption).attr("disabled", true);
                } else {
                    $("#box_" + data[i].idOption).removeAttr("disabled");
                }

                if (data[i].chacked) {
                    $("#box_" + data[i].idOption).attr('checked', 'checked');
                } else {
                    $("#box_" + data[i].idOption).prop('checked', false);
                }
            }
        }
    });
}


$(function () {
    $("a").click(function () {
        console.log(this.id);
        if (this.id.indexOf('modalCall') !== -1) {
            contractId = $('#' + this.id).attr('name');
            console.log(contractId);
            $('#idContract').val(contractId);
            var inputText = $('#selector').val();
            ajaxMethod(inputText, contractId);
        }
    });


    $("#selector").bind('input', function () {
        console.log(contractId);
        var inputText = $('#selector').val();
        ajaxMethod(inputText, contractId);
    });


});