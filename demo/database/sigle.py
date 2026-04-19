import os
import re

parent = os.path.join(os.path.pardir, "src", "main", "java", "com", "visa", "demo","models")
namefiles = os.listdir(parent)

reset = open("Sigle.txt", "w")
reset.write("")
reset.close()

with open("Sigle.txt", "a") as sigle :
    for nf in namefiles:
            with open(os.path.join(parent, nf), "r") as f:
                
                nt = re.compile("\\s*setNomTable\\(\\\"(.*)\\\"\\).*", re.IGNORECASE)
                nsigle = re.compile("\\s*setSigle\\(\\\"(.*)\\\"\\).*", re.IGNORECASE)
                
                vnt = None
                vnsigle = None
                
                for l in f.readlines():
                    if not vnt:
                        vnt = nt.findall(l)
                        
                    if not vnsigle:
                        vnsigle = nsigle.findall(l)
                    
                    if vnt and vnsigle:
                        break
            
            sigle.write(f"{vnsigle[0]} : {vnt[0]}\n")