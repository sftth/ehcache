<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Ehcache Test</title>
		
		<!-- JQuery Framework -->
		<script src="/ehcache/js/jQuery/jquery-3.2.1.js"></script>
		<!-- Pooper Framework -->
		<script src="/ehcache/js/vendor/popper.min.js"></script>
		<!-- Bootstrap Framework -->
		<script src="/ehcache/js/bootstrap/bootstrap.min.js"></script>
		<!-- Bootstrap core CSS -->
		<link href="/ehcache/css/bootstrap.min.css" rel="stylesheet">
	</head>
	<body>
		<div class="container-fluid">
			<div class="raw">
				<h1>Insert data in the ehcache</h1>
				<div class="col-sm">
						CacheKey:<input type="text" name="key" id="key" />
						CacheValue:<input type="text" name="value" id="value" />
						<button type="button" class="btn btn-dark" id="submit">Write</button>
				</div>
				<p><p>
				<h1>Read data in the ehcache from key</h1>				
				<div class="col-sm">
						Enter Key:<input type="text" name="readKey" id="readKkey" />
						Read Value:<input type="text" name="readValue" id="readValue" readonly/>
						<button type="button" class="btn btn-dark" id="readSubmit">Read</button>
				</div>
			</div>
		</div>
	</body>
	<!-- ========================Javascript=================== -->
	<script type="text/javascript">
		$("#submit").click(function(){
			var key = $('#key').val();
			var val = $('#value').val();
			
			$.ajax({
				url:"/ehcache/putCache.json",
				method: "POST",
				data: { key:key, val:val },
				success: function(result) {
					alert("");
				}
			}).done(function(result){
				alert("done");
			}).fail(function(){
				alert("fail");
			});
		});
		
		$("#readSubmit").click(function(){
			var key = $('#readKkey').val();
			
			$.ajax({
				url:"/ehcache/readCache.json",
				method: "POST",
				data: { key:key },
				success: function(result) {
					alert("success");
					var cacheData = result.responseJSON.cacheData;
					$("#readValue").empty();
					$("#readValue").val(cacheData.val);
				},
				
			}).done(function(result){
				alert("done");
			}).fail(function(){
				alert("fail");
			});
		});
	</script>
	
</html>