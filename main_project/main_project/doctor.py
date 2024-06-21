from flask import *
from database import *

doctor=Blueprint('doctor',__name__)

@doctor.route('/doctorhome')
def doctorhome():
    return render_template("doctor_home.html")

@doctor.route('/docappoinment')
def docappointment():
    data={}
    qry="select * from appointment inner join user using(user_id) inner join doctor using(doctor_id)"
    res=select(qry)
    data['view']=res
    return render_template("viewdocappointment.html",data=data)


@doctor.route('/docaddprescription',methods=['get','post'])
def docaddprescription():
    id=request.args['id']
    data={}
    qry="select *from prescription where appointment_id='%s'"%(id)
    data['pre']=select(qry)
    if 'reg' in request.form:
        prescription=request.form['prescription']

        qry="insert into prescription values(null,'%s','%s',curdate())"%(id,prescription)
        insert(qry)
        return redirect(url_for('doctor.docappointment'))
     
    
    return render_template("doctor_addprescription.html",data=data)

@doctor.route("/docviewuser")
def docviewuser():
    data={}
    qry="select * from  user"
    res=select(qry)
    data['view']=res
    return render_template("doc_viewuserdetails.html",data=data)

@doctor.route("/docsendcomplaint",methods=['get','post'])
def docsendcomplaint():
    data={}
    if "submit" in request.form:
        title=request.form['title']
        description=request.form['description']
        u="insert into complaints values(null,'%s','%s','%s','pending',curdate())"%(session['lid'],title,description)
        insert(u)
        return redirect(url_for('doctor.docsendcomplaint'))
    
    return render_template("doc_sendcomplaint.html",data=data)

@doctor.route("/doctorviewreply")
def doctorviewreply():
    data={}
    qry="select * from  complaints where sender_id='%s'"%(session['lid'])  
    res=select(qry)
    data['view']=res
    return render_template("doc_view_reply.html",data=data)