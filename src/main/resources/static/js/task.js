function OnLinkClick(){
		var result = window.confirm('完了したタスクを削除します。よろしいですか?');
		let link = document.getElementById('dl');
		//a要素のhref属性の値を取得する
		let oldHref = link.getAttribute('href');
		
		if(result){
		}else{
			let newHref = oldHref.replace('@{\'/task/delete/\' + ${task.id}}', '@{\'/task/tasklist/}');
			link.setAttribute('href', newHref);
		}
}

function insert(){
	var comment  = $('input[name="comment"]').val();
			if(comment.length == 0 || comment.length > "50"){
				alert("タスク内容が空または文字数オーバーです");
			}else{
				alert("タスク内容が空または文字数オーバーです");
			}
}
