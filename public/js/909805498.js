/* AG-develop 13.01.24-242 (2013-01-24 10:04:33 PST) */
rsinetsegs=['K10981_10058','K10981_10106','K10981_10051','K10981_10089','K10981_10166','K10981_10168','K10981_0'];
    var asiExp=new Date((new Date()).getTime()+2419200000);
    var asiSegs="";
    var rsiPat=/.*_5.*/; 
    var asiPat=/\d{5}_/; 
    var i=0;
    for(x=0;x<rsinetsegs.length&&i<30;++x){if(!rsiPat.test(rsinetsegs[x])){asiSegs+='|'+rsinetsegs[x].replace(asiPat,"");++i;}}
    document.cookie="rsi_segs="+(asiSegs.length>0?asiSegs.substr(1):"")+";expires="+asiExp.toGMTString()+";path=/;domain=.weddingwire.com";
    if(typeof(DM_onSegsAvailable)=="function"){DM_onSegsAvailable(['K10981_10058','K10981_10106','K10981_10051','K10981_10089','K10981_10166','K10981_10168','K10981_0'],'k10981');}