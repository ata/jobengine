//bkLib.onDomLoaded(function() {  });

document.observe('dom:loaded',function(){
	//nicEditors.allTextAreas()
	var advanced_search_show = false;
	function cleanQuickSignIn(){
		if($F('qSignUser') == '' && $F('qSignPass') == ''){
			$('qSignUser').setValue('username or email').setStyle('color: #999');
			$('qSignPass').setValue('password').setStyle('color: #999');
		}
	}
	$('qSignUser').setValue('username or email');
	$('qSignPass').setValue('password');

	$('qSignUser').observe('focus',function(){
		this.setValue('').setStyle('color: #000');
	});
	$('qSignPass').observe('focus',function(){
		this.setValue('').setStyle('color: #000');
	});
	$('qSignUser').observe('blur',function(){
		cleanQuickSignIn();
	});
	$('qSignPass').observe('blur',function(){
		cleanQuickSignIn();
	});
	
	$('ajaxIndicator').setOpacity(0.8);
	$('ajaxIndicator').hide();
	
});