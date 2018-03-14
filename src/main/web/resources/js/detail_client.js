var contractId = 0;
$(function () {
    $("a").click(function () {
        console.log(this.id);
        if (this.id.indexOf('modalCall') !== -1) {
            contractId = $('#' + this.id).attr('name');
            console.log(contractId);
            $('#idContract').val(contractId);
            var inputText = $('#selector').val();
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
                    for (var j = 0; j < data.optionEntities.length; j++) {
                        var flag = false;
                        for (var i = 0; i < data.connectedOptions.length; i++) {
                            if (data.optionEntities[j].idOption == data.connectedOptions[i].idOption) {
                                s += '<input name="checkbox" type="checkbox" checked value="' + data.optionEntities[j].idOption + '"/>' + data.optionEntities[j].nameOption + '<br>';
                                flag = true;
                                break;
                            }
                        }
                        if (!flag) {
                            s += '<input name="checkbox" type="checkbox" value="' + data.optionEntities[j].idOption + '"/>' + data.optionEntities[j].nameOption + '<br>';
                        }
                    }
                    $("#chekboxes").html('').append(s);
                }
            });
        }
    });


    $("#selector").bind('input', function () {
        console.log(contractId);
        var inputText = $('#selector').val();
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
                s += '<h5 style="margin-top: 15px;">Options</h5>';
                for (var j = 0; j < data.optionEntities.length; j++) {
                    var flag = false;
                    for (var i = 0; i < data.connectedOptions.length; i++) {
                        if (data.optionEntities[j].idOption == data.connectedOptions[i].idOption) {
                            s += '<input name="checkbox" type="checkbox" checked value="' + data.optionEntities[j].idOption + '"/>' + data.optionEntities[j].nameOption + '<br>';
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        s += '<input name="checkbox" type="checkbox" value="' + data.optionEntities[j].idOption + '"/>' + data.optionEntities[j].nameOption + '<br>';
                    }
                }
                $("#chekboxes").html('').append(s);
            }
        });
    });

});