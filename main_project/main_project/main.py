from flask import *
from public import public
from admin import admin
from doctor import doctor
from transportation import transportation
from homecare import homecare
from api import api


app=Flask(__name__)
app.secret_key="cgfdfjgh"


app.register_blueprint(public)
app.register_blueprint(admin)
app.register_blueprint(doctor)
app.register_blueprint(transportation)
app.register_blueprint(homecare)
app.register_blueprint(api)

app.run(debug=True,port=5009,host="0.0.0.0")