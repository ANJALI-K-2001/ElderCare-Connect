import uuid
from flask import *
from database import *

homecare=Blueprint('homecare',__name__)


@homecare.route('/homecarehome')
def homecarehome():
    return render_template("homecare_home.html")

@homecare.route("/homecareviewprofile",methods={'get','post'})
def homecareviewprofile():
    data={}
    qry="select * from  homecare where homecare_id='%s'"%(session['tid'])
    res=select(qry)
    data['view']=res
    
    if 'action' in request.args:
        
        id=request.args['id']
        action=request.args['action']
    else:
        action=None
        
    if action=='update':
        qt="select * from homecare  where homecare_id='%s'"%(session['tid'])
        ress=select(qt)
        data['up']=ress
         
    if 'update' in request.form:
        fname=request.form['fname']
        lname=request.form['lname']
        place=request.form['place']
        phone=request.form['phone']
        email=request.form['email']
        image=request.files['image']
        idproof=request.files['idproof']
        gender=request.form['gender']


        
        path="static/"+str(uuid.uuid4())+image.filename
        image.save(path)

        path1="static/"+str(uuid.uuid4())+idproof.filename

        idproof.save(path1)
        
        
        up="update homecare set fname='%s',lname='%s',place='%s',phone='%s',email='%s',image='%s',idproof='%s',gender='%s' where homecare_id='%s'"%(fname,lname,place,phone,email,path,path1,gender,id)
        update(up)

        return '''<script>alert('Update success');window.location='homecareviewprofile'</script>'''


        
    
    return render_template("homecare_viewprofile.html",data=data)

@homecare.route("/homecareviewrequest")
def homecareviewrequest():
    data={}
    qry="select * from  homecare_request"
    res=select(qry)
    data['view']=res
        
    if 'action' in request.args:
        action=request.args['action']
        id=request.args['id']
    else:
        action=None
        
    if action=='accept':
        qry1="update homecare_request set status='ACCEPT' where homecare_request_id='%s'"%(id)
        update(qry1) 
        return ("<script>alert('Accept successfull');window.location='/homecareviewrequest'</script>")


    if action=='reject':
        qry2="update homecare_request set status='Reject' where homecare_request_id='%s'"%(id)
        update(qry2)
    
        return ("<script>alert('Reject successfull');window.location='/homecareviewrequest'</script>")
    

    return render_template("homecare_viewrequest.html",data=data)


@homecare.route("/homecaresendcomplaint",methods=['get','post'])
def homecaresendcomplaint():
    data={}
    qry="select * from  complaints where sender_id='%s'"%(session['lid'])
    res=select(qry)
    data['view']=res
    if "submit" in request.form:
        title=request.form['title']
        description=request.form['description']
        u="insert into complaints values(null,'%s','%s','%s','pending',curdate())"%(session['lid'],title,description)
        insert(u)
        return redirect(url_for('homecare.homecaresendcomplaint'))
    return render_template("homecare_sendcomplaint.html",data=data)



@homecare.route("/homecare_viewuser")
def homecare_viewuser():
    data={}
    qry="select * from user"
    data['view']=select(qry)
    return render_template("homecare_viewuser.html",data=data)


@homecare.route("/homecare_chat_user",methods=['get','post'])
def homecare_chat_user():
    data = {}

    id = request.args['id']

    
    pname = request.args['pname']
    f = "SELECT * FROM chat WHERE sender_id='%s' AND receiver_id='%s' UNION SELECT * FROM chat WHERE sender_id='%s' AND receiver_id='%s' ORDER BY date,time" % (session['lid'], id, id,session['lid'])

    rg = select(f)
    print(rg)

    data['rg'] = rg

    if 'submit' in request.form:
        chat = request.form['chat']
        a = "insert into chat values(null,'%s','homecare','%s','user','%s',curdate(),curtime())" % (
            session['lid'], id, chat)
        insert(a)
        
        return redirect(url_for('homecare.homecare_chat_user', id=id, pname=pname))

    return render_template('homecare_chat_user.html', data=data, pname=pname)



@homecare.route("/homecare_view_caretaker")
def homecare_view_caretaker():
    data={}
    qry="select * from caretaker"
    data['view']=select(qry)
    return render_template("homecare_view_caretaker.html",data=data)


@homecare.route("/homecare_chat_caretaker",methods=['get','post'])
def homecare_chat_caretaker():
    data = {}

    id = request.args['id']

    
    pname = request.args['pname']
    f = "SELECT * FROM chat WHERE sender_id='%s' AND receiver_id='%s' UNION SELECT * FROM chat WHERE sender_id='%s' AND receiver_id='%s' ORDER BY date,time" % (session['lid'], id, id,session['lid'])

    rg = select(f)
    print(rg)

    data['rg'] = rg

    if 'submit' in request.form:
        chat = request.form['chat']
        a = "insert into chat values(null,'%s','homecare','%s','caretaker','%s',curdate(),curtime())" % (
            session['lid'], id, chat)
        insert(a)
        
        return redirect(url_for('homecare.homecare_chat_caretaker', id=id, pname=pname))

    return render_template('homecare_chat_caretaker.html', data=data, pname=pname)