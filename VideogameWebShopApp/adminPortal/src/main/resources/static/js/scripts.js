/**
 * 
 */

/**
 * 
 */

$(document).ready(function() {
	$('.delete-game').on('click', function (){
		/*<![CDATA[*/
	    var path = /*[[@{/}]]*/'remove';
	    /*]]>*/
		
		var id=$(this).attr('id');
		
		bootbox.confirm({
			message: "Are you sure to remove this game? It can't be undone.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Cancel'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirm'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.post(path, {'id':id}, function(res) {
						location.reload();
					});
				}
			}
		});
	});
	
	
	
//	$('.checkboxGame').click(function () {
//        var id = $(this).attr('id');
//        if(this.checked){
//            gameIdList.push(id);
//        }
//        else {
//            gameIdList.splice(gameIdList.indexOf(id), 1);
//        }
//    })
	
	$('#deleteSelected').click(function() {
		var idList= $('.checkboxGame');
		var gameIdList=[];
		for (var i = 0; i < idList.length; i++) {
			if(idList[i].checked==true) {
				gameIdList.push(idList[i]['id'])
			}
		}
		
		console.log(gameIdList);
		
		/*<![CDATA[*/
	    var path = /*[[@{/}]]*/'removeList';
	    /*]]>*/
	    
	    bootbox.confirm({
			message: "Are you sure to remove all selected games? It can't be undone.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Cancel'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirm'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.ajax({
						type: 'POST',
						url: path,
						data: JSON.stringify(gameIdList),
						contentType: "application/json",
						success: function(res) {
							console.log(res); 
							location.reload()
							},
						error: function(res){
							console.log(res); 
							location.reload();
							}
					});
				}
			}
		});
	});
	
	$("#selectAllGames").click(function() {
		if($(this).prop("checked")==true) {
			$(".checkboxGame").prop("checked",true);
		} else if ($(this).prop("checked")==false) {
			$(".checkboxGame").prop("checked",false);
		}
	})
});