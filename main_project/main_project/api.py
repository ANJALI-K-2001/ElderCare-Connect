from flask import *
from database import *

import uuid
from datetime import datetime
from date1 import *


api=Blueprint("api",__name__)




@api.route("/user_login")
def user_login():
    data={}

    username=request.args['uname']
    pwd=request.args['pwd']

    print(username,pwd)

    qry="select * from login where username='%s' and password='%s'"%(username,pwd)
    res=select(qry)


    if res:
        data['status']='success'
        data['data']=res
    else:
        data['status']='failed'


    return str(data)


        
@api.route("/userreg")
def userreg():
    data={}
    fname=request.args['fname']
    lname=request.args['lname']
    place=request.args['place']
    phone=request.args['phone']
    email=request.args['email']
    gender=request.args['gender']
    age=request.args['age']
    care_id=request.args['care_id']
    username=request.args['username']
    pwd=request.args['pass']

    qry="insert into login values(null,'%s','%s','user')"%(username,pwd)
    print(qry)
    id=insert(qry)

    qry1="insert into user values(null,(select caretaker_id from caretaker where login_id='%s'),'%s','%s','%s','%s','%s','%s','%s','%s')"%(care_id,id,fname,lname,place,phone,email,gender,age)
    res=insert(qry1)
    print(qry1)

    if res:
        data['status']="success"
    else :
        data['status']='failled'

    return str(data)


@api.route('/viewuser')
def viewuser():
    caretaker_id=request.args['caretaker_id']
    data={}
    qry="select * from user where caretaker_id=(select caretaker_id from caretaker where login_id='%s')"%(caretaker_id)
    res=select(qry)
    print(res)

    if res:
        data['status']="success"
        data['data']=res
    else:
        data['status']='failed'
    data['method']="viewuser"

    return str(data)


@api.route('/viewdsymptoms')
def viewdsymptoms():
    data={}
    diseaseid=request.args['diseaseid']
    qry="select * from symptoms where disease_id='%s'"%(diseaseid)
    res=select(qry)

    print(res)

    if res:
        data['status']="success"
        data['data']=res
    else:
        data['status']='failed'
    data['method']="viewsymptoms"

    return str(data)

@api.route('/viewdmedicine')
def viewdmedicine():
    data={}
    diseaseid=request.args['diseaseid']

    qry="select * from medicine where disease_id='%s'"%(diseaseid)
    res=select(qry)
    
    print(res)

    if res:
        data['status']="success"
        data['data']=res
    else:
        data['status']='failed'
    data['method']="viewdmedicine"

    return str(data)

@api.route('/sendenquiry')
def usersendenquiry():
    data={}
    userid=request.args['user_id']
    title=request.args['title']
    description=request.args['description']
    
    qry="insert into complaints values(null,'%s','%s','%s','pending',curdate())"%(userid,title,description)
    res=insert(qry)
    
    print(res)

    if res:
        data['status']="success"
    else:
        data['status']='failed'
    data['method']='sendenquiry'
   
    return str(data)

@api.route('/viewenquiry')
def viewenquiry():

    data={}
    userid=request.args['user_id']
    qry="select * from complaints where sender_id='%s'"%(userid)
    res=select(qry)
    print(res)

    if res:
        data['status']="success"
        data['data']=res
    else:
        data['status']='failed'
    data['method']="viewenquiry"

    return str(data)


@api.route('/viewdoctors')
def viewdoctors():

    data={}

    qry="select * from doctor"
    res=select(qry)
    print(res)

    if res:
        data['status']="success"
        data['data']=res
    else:
        data['status']='failed'
    data['method']="vewdoctors"

    return str(data)







@api.route('/viewuploadsimages')    
def viewuploadsimages():

    data={}
    userid=request.args['user_id']
    qry="select * from uploads where user_id=(select user_id from user where login_id='%s')"%(userid)
    res=select(qry)
    print(res)

    if res:
        data['status']="success"
        data['data']=res
    else:
        data['status']='failed'
    data['method']="viewuploads"

    return str(data)


# @api.route('/user_make_appointment',methods=['POST'])
# def user_make_appointment():
#     data={}
#     path=request.files['image']
#     img="static/appointmentimages/"+str(uuid.uuid4())+path.filename
#     path.save(img)
#     id=request.form['user_id']
#     doctors_id=request.form['doctors_id']
#     date=request.form['date']
#     print(date)
    
#     qry="insert into uploads values(null,(select user_id from user where login_id='%s'),'%s','pending','%s')"%(id,img,date)
#     uploadid=insert(qry)

#     qry2="insert into appointment values(null,'%s',(select user_id from user where login_id='%s'),'%s','%s','pending')"%(doctors_id,id,uploadid,date)
#     res=insert(qry2)

#     print(res)

#     data['status']="success"
    
#     return str(data)


@api.route('/viewappointment')
def viewappointment():
    data={}

    id=request.args['user_id']

    qry="select * from appointment where user_id=(select user_id from user where login_id='%s')"%(id)
    res=select(qry)
    print(res)

    if res:
        data['status']="success"
        data['data']=res
    else:
        data['status']="failed"
    data['method']="viewappointment"

    return str(data)




# @api.route('/viewappointment_image')
# def viewappointment_image():
#     data={}

#     uploads_id=request.args['uppid']

#     qry="select * from uploads where uploads_id='%s'"%(uploads_id)
#     res=select(qry)

#     print(res)

#     if res:
#         data['status']="success"
#         data['data']=res
#     else:
#         data['status']="failed"
#     data['method']="viewappointmentimages"

#     return str(data)


@api.route('/viewprescription')
def viewprescription():
    data={}
    appid=request.args['appid']
    qry="SELECT * FROM prescription INNER JOIN appointment USING(appointment_id)  WHERE appointment_id='%s'"%(appid)
    res=select(qry)
    print(res,']]]]]]]]]]]]]]]]]]]]]]]]]]]')
    if res:
        data['status']="success"
        data['data']=res
    else:
        data['status']="failed"
    data['method']="viewprescription"
    return str(data)


@api.route('/user_add_medicine',methods=['POST'])
def user_addmedicine():
    data={}

    path=request.files['image']
    
    img="static/medicinedetailsimages/"+str(uuid.uuid4())+path.filename
    path.save(img)

    medicine=request.form['medicine']
    id=request.form['user_id']

    qry="insert into medicine values(null,'%s','%s','%s')"%(id,medicine,img)
    res=insert(qry)

    print(res)

    if res:
        data['status']="success"
    else:
        data['status']='failed'
    data['method']='addmed'
     
    return str(data)

@api.route('/set_reminder')
def set_reminder():
    data={}

    med_id=request.args['med_id']
    start_date=request.args['start_date']
    end_date=request.args['end_date']
    time_mrg=request.args['time_mrg']
    time_afn=request.args['time_afn']
    time_nyt=request.args['time_nyt']


    dates=generate_dates(start_date,end_date)
    print(dates)
    

    for i in dates:
        print(i)
        qry="insert into set_alert values(null,'%s','%s','%s','%s','%s','%s','alert_set')"%(med_id,i,i,time_mrg,time_afn,time_nyt)
        insert(qry)

    data['status']='success'

    return str (data)


  
@api.route('/medicine_details')
def medicine_details_view():
    data={}

    user_id=request.args['user_id']

    qry="select * from medicine where user_id='%s'"%(user_id)
    res=select(qry)

    print(res)

    if res:
        data['status']="success"
        data['data']=res
    else:
        data['status']="failed"
    data['method']="medicinedetailsview"

    return str(data)





# @api.route('/notification')
# def notificationalert():
#     data={}
#     lid=request.args['log_id']
#     qry="SELECT * FROM `set_alert` INNER JOIN medicine_details USING(medicine_det_id) WHERE user_id=(select user_id from user where login_id='%s')"%(lid)
#     res=select(qry)
#     start_date=res[0]['start_date']
#     end_date=res[0]['end_date']
#     print(res)
#     dates=generate_dates(start_date,end_date)
#     print(dates)
#     date=datetime.today().strftime('%d/%m/%Y')
#     for i in dates:
#         if date==i:
#             data['status']='success'
#             print(data['status'])
#         else:
#             data['data']=res
#     print(data)
    
#     return str(data)



@api.route('/notification')
def notificationalert():
    data={}
    lid=request.args['log_id']
    date=datetime.today().strftime('%d/%m/%Y')
    print("Date : ",date)

    # qry="SELECT * FROM `set_alert` INNER JOIN medicine USING(medicine_det_id) WHERE user_id=(select user_id from user where login_id='%s') and start_date='%s'"%(lid,date)
    # res=select(qry)
    qrt="SELECT * FROM `set_alert` INNER JOIN `medicine` USING(medicine_id) WHERE user_id = (SELECT user_id FROM `user` WHERE caretaker_id = (SELECT caretaker_id FROM `caretaker` WHERE login_id = '%s')  LIMIT 1) and start_date='%s' ;"%(lid,date)
    res=select(qrt)
    if res:
        data['status']="success" 
        data['data']=res    
    else:
        data['status']="failed"


    # start_date=res[0]['start_date']
    # end_date=res[0]['end_date']
    # print(res)
    # for r in res:
    #     # print(r['start_date'])
    #     # print(r['end_date'])
    #     dates=generate_dates(r['start_date'],r['end_date'])
    #     print(dates)
    #     date=datetime.today().strftime('%d/%m/%Y')
    #     print("Date : ",date)
    #     print(type(date),"......")
    #     for i in dates:
    #         print("I : ",i)
    #         print(type(i),"/////////")
    #         data['status']='success'
    #         data['data']=res
            
            # if str(date) == str(i):
            #     data['status']='success'
            #     data['data']=res
            #     # print(data['status'])
            # else:
            #     data['data']=res
            #     data['status']='success'
        # print(data)
    
    return str(data)



# ///////////////////////////////////////////////




@api.route('/viewmessg')
def viewmessages():
    data={}
    
    logid = request.args['logid']

    qry="select * from message inner join user using(user_id) where login_id='%s'"%(logid)
    res=select(qry)
    print(res)

    if res:
        data['status']="success"
        data['data']=res
    else:
        data['status']='failed'
    data['method']="viewmessages"

    return str(data)


@api.route('/accidentdetect',methods=['get','post'])
def accidentdetect():
    data = {}
    logid = request.args['logid'] 	
    latitude = request.args['latitude']
    longitude = request.args['longitude']
    # date = request.args['date']
    # status = request.args['status']
    import datetime
    x = datetime.datetime.now()
    print(x)

    q="select * from accident where user_id=(select user_id from user where login_id='%s') and datetime like '%s'" %(logid,x)

    print(q)
    res=select(q)
    if res:
        data['status'] = 'failed'
    else:
        print("HII")
        q = "insert into accident values(null,(select user_id from user where login_id='%s'),'%s','%s','%s','Pending')" % (logid,latitude,longitude,x)
        id = insert(q)
        if id > 0:
            data['status'] = 'success'
        else:
            data['status'] = 'failed'
    data['method']="accidentdetect"
    return str(data)



@api.route("/viewhomecare")
def viewhomecare():
    data={}
    qry="select * from homecare"
    res=select(qry)
    if res:
        data['data']=res
        data['status']='success'
    else:
        data['status']='failed'
    data['method']='view'
    return str(data)



@api.route('/chat',methods=['get','post'])
def chat():
    data={}
    
    chat=request.args['chat']
    sender_id=request.args['sender_id']
    rid=request.args['rid']
    
    print(sender_id,'/////////////////////////////////////////////////')
    
    
    qry="insert into chat values(null,'%s','caretaker','%s','homecare','%s',curdate(),curtime())"%(sender_id,rid,chat)
    res=insert(qry)
    if res:
        data['status']="success"
        
        data['method']="done"
    else:
        data['status']="failed"
        data['method']="done"
    return str(data)

    
@api.route('/viewchat')
def viewchat():
    user_id=request.args['sender_id']
    rid=request.args['receiver_id']
    
    data={}

    f="SELECT * FROM chat WHERE sender_id='%s' AND receiver_id='%s' UNION SELECT * FROM chat WHERE sender_id='%s' AND receiver_id='%s' ORDER BY date,time"%(user_id,rid,rid,user_id)
    rg=select(f)
    if rg:
        data['status']="success"
        data['data']=rg
        data['method']="view"
    else:
        data['status']="failed"
        data['method']="view"
    
    return str(data)





@api.route('/userchat',methods=['get','post'])
def userchat():
    data={}
    
    chat=request.args['chat']
    sender_id=request.args['sender_id']
    rid=request.args['rid']
    
    print(sender_id,'/////////////////////////////////////////////////')
    
    
    qry="insert into chat values(null,'%s','user','%s','homecare','%s',curdate(),curtime())"%(sender_id,rid,chat)
    res=insert(qry)
    if res:
        data['status']="success"
        
        data['method']="done"
    else:
        data['status']="failed"
        data['method']="done"
    return str(data)




@api.route("/viewemergency")
def viewemergency():
    caretaker_id=request.args['caretaker_id']
    data={}
    qry="SELECT * FROM `emergency_alert` INNER JOIN `user` USING(user_id)  WHERE  user.caretaker_id=(SELECT caretaker_id FROM `caretaker` WHERE login_id ='%s')"%(caretaker_id)
    res=select(qry)
    if res:
        data['data']=res
        data['status']='success'
    else:
        data['status']='failed'
    data['method']='view'
    return str(data)





@api.route("/update_viewemergency")
def update_viewemergency():
    emergency_alert_id=request.args['emergency_alert_id']
    data={}
    qry="update emergency_alert set status='update' where emergency_alert_id='%s'"%(emergency_alert_id)
    res=update(qry)
    if res:
        data['data']=res
        data['status']='success'
    else:
        data['status']='failed'
    data['method']='update'
    return str(data)



@api.route('/user_make_appointment')
def user_make_appointment():
    data={}

    id=request.args['user_id']
    date=request.args['date']
    reason=request.args['reason']
    doctors_id=request.args['doctors_id']
    print(date)
    

    qry2="insert into appointment values(null,(select user_id from user where login_id='%s'),'%s','%s','%s',now(),500,'pending')"%(id,doctors_id,reason,date)
    res=insert(qry2)

    print(res)

    data['status']="success"
    
    return str(data)




@api.route('/makepayment')
def makepayment():
    data={}
    appointmentid=request.args['appointmentid']

    qry="insert into payment values (null,'%s',500,curdate(),'PAID')"%(appointmentid)
    ress=insert(qry)
    qryt="update  appointment set status='PAID' where appointment_id='%s'"%(appointmentid)
    res=update(qryt)

    if res:
        data['status']="success"
    else:
        data['status']='failed'
    return str(data)



@api.route('/sendrequest_homecare')
def sendrequest_homecare():
    data={}
    userid=request.args['user_id']
    homecare_id=request.args['homecare_id']
    description=request.args['description']
    
    qry="insert into homecare_request values(null,(select user_id from user where login_id='%s'),'%s','%s',curdate(),'pending')"%(userid,homecare_id,description)
    res=insert(qry)
    
    print(res)

    if res:
        data['status']="success"
    else:
        data['status']='failed'
    data['method']='sendenquiry'
   
    return str(data)

@api.route('/viewrequest_homecare')
def viewrequest_homecare():

    data={}
    userid=request.args['user_id']
    qry="select * from homecare_request where user_id=(select user_id from user where login_id='%s')"%(userid)
    res=select(qry)
    print(res)

    if res:
        data['status']="success"
        data['data']=res
    else:
        data['status']='failed'
    data['method']="viewenquiry"

    return str(data)



@api.route('/user_send_feedback')
def user_send_feedback():
    data={}
    userid=request.args['user_id']
    feedback=request.args['feedback']
    receiver_id=request.args['receiver_id']
    
    qry="insert into feedback values(null,'%s','%s','%s',curdate())"%(userid,receiver_id,feedback)
    res=insert(qry)
    

    print(res)

    if res:
        data['status']="success"
    else:
        data['status']='failed'
    data['method']='sendenquiry'
   
    return str(data)



@api.route('/user_send_emergency_request')
def user_send_emergency_request():
    data={}
    user_id=request.args['user_id']
    title=request.args['title']
   
   
    qry="insert into emergency_alert values(null,'%s','%s',curdate(),now(),'pending')"%(user_id,title)
    res=insert(qry)
    

    print(res)

    if res:
        data['status']="success"
    else:
        data['status']='failed'
    data['method']='sendenquiry'
   
    return str(data)





@api.route("/viewtransportation")
def viewtransportation():
    data={}
    qry="select * from transportation"
    res=select(qry)
    if res:
        data['data']=res
        data['status']='success'
    else:
        data['status']='failed'
    data['method']='view'
    return str(data)



@api.route('/sendrequest_transportation')
def sendrequest_transportation():
    data={}
    userid=request.args['user_id']
    transportation_id=request.args['transportation_id']
    description=request.args['description']
    
    qry="insert into transportation_request values(null,(select user_id from user where login_id='%s'),'%s','%s',curdate(),'pending')"%(userid,transportation_id,description)
    res=insert(qry)
    
    print(res)

    if res:
        data['status']="success"
    else:
        data['status']='failed'
    data['method']='sendenquiry'
   
    return str(data)


@api.route('/viewrequest_transportation')
def viewrequest_transportation():

    data={}
    userid=request.args['user_id']
    qry="select * from transportation_request where user_id=(select user_id from user where login_id='%s')"%(userid)
    res=select(qry)
    print(res)

    if res:
        data['status']="success"
        data['data']=res
    else:
        data['status']='failed'
    data['method']="viewenquiry"

    return str(data)