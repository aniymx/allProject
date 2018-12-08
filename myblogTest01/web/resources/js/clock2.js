var clock = {
    init:function(dom){

        var canvasDom = document.getElementById(dom);
        var ctx = canvasDom.getContext("2d");
        var width = canvasDom.width;
        var height = canvasDom.height;
        var r = width / 2;
        var rem = width / 200;
        this.draw(ctx,r,1,width,height);
    },
    drawClick(ctx,r,rem,hour,minute,second){
        ctx.save()
        ctx.translate(r,r);
        ctx.beginPath();
        ctx.lineWidth = 10 * rem;
        ctx.arc(0,0,r-ctx.lineWidth/2,0,2*Math.PI,false);
        ctx.stroke();
        ctx.font = 16* rem+"px Microsoft YaHei";
        var numArr = [3,4,5,6,7,8,9,10,11,12,1,2];
        ctx.textAlign = "center";
        ctx.textBaseline = "middle";
        numArr.forEach(function(num,i){
            var rad = 2 * Math.PI / 12 * i;
            var x = Math.cos(rad) * (r-30* rem);
            var y = Math.sin(rad) * (r-30* rem);
            ctx.fillText(num,x,y);
        });

        for (var i = 0; i < 60; i++) {
            var rad = 2 * Math.PI / 60 * i;
            var x = Math.cos(rad) * (r - 18* rem);
            var y = Math.sin(rad) * (r - 18* rem);
            ctx.beginPath();
            if(i % 5 == 0){
                ctx.fillStyle = "#000";
                /*ctx.rotate(rad);
                 ctx.fillRect(x,y,2,10);*/
                ctx.arc(x,y,2* rem,0,2*Math.PI,false);
            }else{

                ctx.fillStyle = "#ccc";
                ctx.arc(x,y,2* rem,0,2*Math.PI,false);
            }

            ctx.fill();
        }

        ctx.save();
        ctx.beginPath();
        var hrad = 2 * Math.PI / 12 * hour;
        var hhrad = 2 * Math.PI /12 / 60 * minute;
        ctx.rotate(hrad+hhrad);
        ctx.lineWidth = 6* rem;
        ctx.lineCap = "round";
        ctx.moveTo(0,10* rem);
        ctx.lineTo(0,-r/2);
        ctx.stroke();
        ctx.restore();


        ctx.save();
        ctx.beginPath();
        var mrad = 2 * Math.PI / 60 * minute;
        ctx.rotate(mrad);
        ctx.lineWidth =  3* rem;
        ctx.lineCap = "round";
        ctx.moveTo(0,10* rem);
        ctx.lineTo(0,-r+30* rem);
        ctx.stroke();
        ctx.restore();

        ctx.save();
        ctx.beginPath();
        ctx.fillStyle = "#ab4343";
        var srad = 2 * Math.PI / 60 * second;
        ctx.rotate(srad);
        ctx.lineWidth =  3* rem;
        ctx.lineCap = "round";
        ctx.moveTo(-2* rem,20* rem);
        ctx.lineTo(2* rem,20* rem);
        ctx.lineTo(1,-r+18* rem);
        ctx.lineTo(-1,-r+18* rem);
        ctx.fill();
        ctx.restore();

        ctx.beginPath();
        ctx.fillStyle = "white";
        ctx.arc(0,0,3* rem,0,2*Math.PI,false);
        ctx.fill()
        ctx.restore();
    },
    draw:function(ctx,r,rem,width,height){



        setInterval(function(){
            var d = new Date();
            var hour = d.getHours();
            var minute = d.getMinutes();
            var second = d.getSeconds();
            ctx.clearRect(0,0,width,height);
            clock.drawClick(ctx,r,rem,hour,minute,second);
        },1000);
    }
}