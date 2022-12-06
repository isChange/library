<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>充值功能</title>

<link rel="stylesheet" type="text/css" href="css/amazeui.min.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" />

</head>
<body>
<div class="pay">
	<!--主内容开始编辑-->
	<div class="tr_recharge">
		<div class="tr_rechtext">
			<p class="te_retit"><img src="images/coin.png" alt="" />充值中心</p>
			<p>1.书籍是人类进步的阶梯。</p>
			<p>2.虚拟币与人民币换算为1：1，即1元=1币，你可以选择支付宝或者是微信的付款方式来进行充值，币每次10个起充。</p>
		</div>
			<div class="tr_rechbox">
				<div class="tr_rechhead">
					<img src="images/ys_head2.jpg" />
					<p>充值帐号：
						<a>${sessionScope.get("readercard").username}</a>
					</p>
					<div class="tr_rechheadcion">
						<img src="images/coin.png" alt="" />

					</div>
				</div>
				<div class="tr_rechli am-form-group">
					<ul class="ui-choose am-form-group" id="uc_01">
						<li>
							<label class="am-radio-inline">
									<input id="r1" type="radio"  value="" name="docVlGender" required data-validation-message="请选择一项充值额度"> 10
								</label>
						</li>
						<li>
							<label class="am-radio-inline">
									<input id="r2" type="radio" name="docVlGender" data-validation-message="请选择一项充值额度"> 20
								</label>
						</li>

						<li>
							<label class="am-radio-inline">
									<input id="r3" type="radio" name="docVlGender" data-validation-message="请选择一项充值额度"> 50
								</label>
						</li>
						<li>
							<label class="am-radio-inline">
									<input type="radio" name="docVlGender" data-validation-message="请选择一项充值额度"> 其他金额
								</label>
						</li>
					</ul>

				</div>
				<div class="tr_rechoth am-form-group">
					<span>其他金额：</span>
					<input type="number" min="10" max="10000" value="10.00元" class="othbox" data-validation-message="充值金额范围：10-10000元" />
					<!--<p>充值金额范围：10-10000元</p>-->
				</div>
				<h3><font size="2" color="red">不支持微信支付</font> </h3>
				<div class="tr_rechcho am-form-group">
					<span>充值方式：</span>

					<label class="am-radio">
							<input disabled="disabled" type="radio" name="radio1" value="" data-am-ucheck required data-validation-message="请选择一种充值方式"><img src="images/wechatpay.png">
						</label>
					<label class="am-radio" style="margin-right:30px;">
							<input type="radio" id="radio1" value="" data-am-ucheck data-validation-message="请选择一种充值方式"><img src="images/zfbpay.png">
						</label>
				</div>
				<div class="tr_rechnum">
					<span>应付金额：</span>
					<p id="price" class="rechnum">0.00元</p>
				</div>
			</div>
			<div class="tr_paybox">
				<input type="button" id="btn" value="确认支付" class="tr_pay am-btn" />
				<span>温馨提示：币只限于本系统使用，遇到问题请拨打联系电话。</span>
			</div>

	</div>
</div>
<div id="pay"></div>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/amazeui.min.js"></script>
<script type="text/javascript" src="js/ui-choose.js"></script>
<script type="text/javascript">
	$("#btn").click(function () {
		if($("#radio1")[0].checked){
			if (confirm("你确定支付吗")){
				$.ajax({
					url:'http://localhost:8080/library/api/ali-pay/trade/page/pay',
					type:'POST',
					data:{
						price: $("#price").text().split(".")[0]
					},
					dataType:'json',
					success:function (resp) {
						if (resp.code == 0) {
							$('body').append(resp.data)
						}
					}
				})

			}else {

				return false;
			}
		}else {
			alert("请选择支付类型！！")
		}

	})

	// 将所有.ui-choose实例化
	$('.ui-choose').ui_choose();
	// uc_01 ul 单选
	var uc_01 = $('#uc_01').data('ui-choose'); // 取回已实例化的对象
	uc_01.click = function(index, item) {
		console.log('click', pay, item.text())
	}
	uc_01.change = function(index, item) {
		console.log('change', pay, item.text())
	}
	$(function() {
		$('#uc_01 li:eq(3)').click(function() {
			$('.tr_rechoth').show();
			$('.tr_rechoth').find("input").attr('required', 'true')
			$('.rechnum').text('10.00元');
		})
		$('#uc_01 li:eq(0)').click(function() {
			$('.tr_rechoth').hide();
			$('.rechnum').text('10.00元');
			$('.othbox').val('');
		})
		$('#uc_01 li:eq(1)').click(function() {
			$('.tr_rechoth').hide();
			$('.rechnum').text('20.00元');
			$('.othbox').val('');
		})
		$('#uc_01 li:eq(2)').click(function() {
			$('.tr_rechoth').hide();
			$('.rechnum').text('50.00元');
			$('.othbox').val('');
		})
		$(document).ready(function() {
			$('.othbox').on('input propertychange', function() {
				var num = $(this).val();
				$('.rechnum').html(num + ".00元");
			});
		});
	})

	$(function() {
		$('#doc-vld-msg').validator({
			onValid: function(validity) {
				$(validity.field).closest('.am-form-group').find('.am-alert').hide();
			},
			onInValid: function(validity) {
				var $field = $(validity.field);
				var $group = $field.closest('.am-form-group');
				var $alert = $group.find('.am-alert');
				// 使用自定义的提示信息 或 插件内置的提示信息
				var msg = $field.data('validationMessage') || this.getValidationMessage(validity);

				if(!$alert.length) {
					$alert = $('<div class="am-alert am-alert-danger"></div>').hide().
					appendTo($group);
				}
				$alert.html(msg).show();
			}
		});
	});
</script>

</body>
</html>