/**
 * Created by lixindi on 2017/3/21.
 */
var init = function () {
    var timer = document.getElementById("timer");
    timer.innerHTML = "00:00:00";
    hour = minute = second = 0;
    clearTimeout(t);
};
var hour, minute, second;
var t;
function startit() {
    second++;
    if (second >= 60) {
        second = 0;
        minute++;
    }
    if (minute >= 60) {
        minute = 0;
        hour++;
    }
    timer.innerHTML = j(hour) + ":" + j(minute) + ":" + j(second);
    t = setTimeout("startit()", 1000);
}
var j = function (arg) {
    return arg >= 10 ? arg : "0" + arg;
};