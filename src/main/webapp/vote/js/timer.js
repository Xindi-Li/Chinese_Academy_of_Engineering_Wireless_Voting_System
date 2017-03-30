/**
 * Created by lixindi on 2017/3/27.
 */
var init = function () {
    var timer = document.getElementById("timer");
    timer.innerHTML = "00:01:00";
    hour = 0;
    second = 0;
    minute = 1;
    clearTimeout(t);
};
var hour, minute, second;
var t;
function startit() {
    second--;
    if (second < 0) {
        second = 59;
        minute--;
    }
    if (minute < 0) {
        hour--;
        if (hour < 0) {
            clearTimeout(t);
            alert("您未在规定的时间内完成投票，选票作废");
            window.location.href = "about:blank";
            return;
        } else {
            minute = 59;
        }
    }
    timer.innerHTML = j(hour) + ":" + j(minute) + ":" + j(second);
    t = setTimeout("startit()", 1000);
}
var j = function (arg) {
    return arg >= 10 ? arg : "0" + arg;
};