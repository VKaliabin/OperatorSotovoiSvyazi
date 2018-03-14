$(function () {
    $(":checkbox").change(function () {
        var checkValues = $('input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();

        var idContract = $("#idContract").val();
        var idTariff = $("#idTariff").val();

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

    });
});