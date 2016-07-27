# CamelStudy

path : http://52.23.170.75:5000/model1

sample JSON input: 
{
    "id": "newmmmmaaaa2",
    "text" : "LEGO MARVEL SUPER HEROES Age 7-14 76031THE HULK BUSTER SMASH 248 pcs/pzs WARNING: CHOKING HAZARD TOY CONTAINS SMALL PARTS AND A SMALL BALL.NOT FOR CHILDREN UNDER 3 YEARS.AVENGERSAGE OF ULTRONNEW SUPER JUMPER"
    
}


Post 
request:
 http://52.23.170.75:5000/model1
 Content-type : application/json
 
response : 

{"id":"myid","Raw_Data":"lego marvel super heroes age 7-14 76031the hulk buster smash 248 pcs\/pzs warning: choking hazard toy contains small parts and a small ball.not for children under 3 years.avengersage of ultronnew super jumper","Brand":"lego","Age":"7-14","Warning":"choking hazard","Pieces":"248","Players":"nil","Net_Weight":"Nil"}


_________________________________________________________
Integartion for Attributes from Abzooba
-----------------------------------------------------------

URL:http://localhost:9090/ocr/rest/abzoobaParse/parseText
POST
Content-type: application/json

Request Body :
 { "id": "newmmmmaaaa2", "text" : "LEGO MARVEL SUPER HEROES Age 7-14 76031THE HULK BUSTER SMASH 248 pcs/pzs WARNING: CHOKING HAZARD TOY CONTAINS SMALL PARTS AND A SMALL BALL.NOT FOR CHILDREN UNDER 3 YEARS.AVENGERSAGE OF ULTRONNEW SUPER JUMPER"

}

Response:

{"id":"newmmmmaaaa2","Brand":"Lego","Age":"7-14","Warning":"choking hazard","Raw_Data":"LEGO MARVEL SUPER HEROES Age 7-14 76031THE HULK BUSTER SMASH 248 pcs/pzs WARNING: CHOKING HAZARD TOY CONTAINS SMALL PARTS AND A SMALL BALL.NOT FOR CHILDREN UNDER 3 YEARS.AVENGERSAGE OF ULTRONNEW SUPER JUMPER","Pieces":"248"}


