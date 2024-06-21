from flask import *
from database import *

admin=Blueprint('admin',__name__)

@admin.route('/adminhome')
def adminhome():
    return render_template("admin_home.html")


@admin.route("/adminviewuser")
def adminviewuser():
    data={}
    qry="select * from  user"
    res=select(qry)
    data['view']=res
    return render_template("viewusers.html",data=data)


@admin.route("/adminviewcomplaints")
def adminviewcomplaints():
    data={}
    qry="select * from  complaints"
    res=select(qry)
    data['view']=res
    return render_template("viewcomplaints.html",data=data)

@admin.route("/adminverifydoctors")
def adminverifydoctors():
    data={}
    qry="select * from  doctor inner join login using(login_id) where usertype='pending' "
    res=select(qry)
    data['view']=res
    print("______________________________________",res)
    print("**************************",data['view'])
    
    if 'action' in request.args:
        action=request.args['action']
        id=request.args['id']
    else:
        action=None

    if action=='accept':
        verifyqry="update login set usertype='doctor' where login_id='%s'"%(id)
        update(verifyqry)
        return redirect(url_for('admin.adminverifydoctors'))
    
    if action=='reject':
        cancelqry="update login set usertype='pending' where login_id='%s'"%(id)
        update(cancelqry)
        return redirect(url_for('admin.adminverifydoctors'))
    
    
    return render_template("verifydoctors.html",data=data)
    
@admin.route("/adminsendreply",methods=['get','post'])
def adminsendreply():
    id=request.args['id']
    data={}
    if "submit" in request.form:
        reply=request.form['reply']
        u="update complaints set reply='%s' where complaint_id='%s'"%(reply,id)
        update(u)
        return redirect(url_for('admin.adminviewcomplaints'))
    
    return render_template("sendreply.html",data=data)

@admin.route("/adminviewcaretakers")
def adminviewcaretakers():
    data={}
    qry="select * from  caretaker"
    res=select(qry)
    data['view']=res
    return render_template("viewcaretakers.html",data=data)


@admin.route("/adminviewfeedback")
def adminviewfeedback():
    data={}
    qry="select * from  feedback"
    res=select(qry)
    data['view']=res
    return render_template("viewfeedback.html",data=data)
    
@admin.route("/adminverifytransportation")
def adminverifytransportation():
    data={}
    qry="select * from  transportation "
    res=select(qry)
    data['view']=res
    if 'action' in request.args:
        action=request.args['action']
        transportation_request_id=request.args['id']
    else:
        action=None

    if action=='accept':
        verifyqry="update transportation_request set status='verified' where transportation_request_id='%s'"%(transportation_request_id)
        update(verifyqry)
        return redirect(url_for('admin.adminverifytransportation'))
    
    if action=='reject':
        cancelqry="update transportation_request set status='rejected' where transportation_request_id='%s'"%(transportation_request_id)
        update(cancelqry)
        return redirect(url_for('admin.adminverifytransportation'))
    
    
    return render_template("verifytransportation.html",data=data)

@admin.route("/adminverifyhomecare")
def adminverifyhomecare():
    data={}
    qry="select * from  homecare "
    res=select(qry)
    data['view']=res
    if 'action' in request.args:
        action=request.args['action']
        homecare_request_id=request.args['id']
    else:
        action=None

    if action=='accept':
        verifyqry="update homecare_request set status='verified' where homecare_request_id='%s'"%(homecare_request_id)
        update(verifyqry)
        return redirect(url_for('admin.adminverifyhomecare'))
    
    if action=='reject':
        cancelqry="update homecare_request set status='rejected' where homecare_request_id='%s'"%(homecare_request_id)
        update(cancelqry)
        return redirect(url_for('admin.adminverifyhomecare'))
    return render_template("verifyhomecare.html",data=data)
