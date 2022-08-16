import random
print("############# WELCOME TO QUIZ GAME #################\n\n")

mode = input("Enter 'YES' for MULTIPLE CHOICE question otherwise press 'NO'")
quiz = [["aotearoa","new zealand"],["karakia","prayer"],["kai","food"],["ki ora","hello"],["morena","morning"],["whanau","family"],["whenua","land"],["aroha","love"],["awa","river"],["hangi","traditional fest"],["hapu","clan"],["hikoki","walk"],["hui","meeting"],["iwi","tribe"],["kaumatua","elder"],["kauri","large native conifer"],["koha","gift"],["kohanga reo","language nest"],["mahi","work"],["mana","reputation"],["manuhiri","guests"],["maori","indigenous inhabitants of New Zealand"],["marae","meeting grounds"],["maunga","mountain"],["moa","extinct bird"],["moana","sea"],["motu","island"],["nui","large"],["pa","hill fort"],["pakeha","european"],["pounamu","greenstone"],["puku","stomach"],["rangatira","boss"],["taihoa","to wait"],["tama","som"],["tamahine","daughter"],["tamariki","children"],["tane","man"],["tangi","funeral"],["taonga","anything precious"],["tapu","sacred"],["te reo maori","maori language"],["tipuna","ancestor"],["wahini","woman"],["wai","water"],["waka","canoe"],["whakapapa","genealogy"],["iti","small"],["haka","dance"]]
quiz_op = [["aotearoa","new zealand"],["karakia","prayer"],["kai","food"],["ki ora","hello"],["morena","morning"],["whanau","family"],["whenua","land"],["aroha","love"],["awa","river"],["hangi","traditional fest"],["hapu","clan"],["hikoki","walk"],["hui","meeting"],["iwi","tribe"],["kaumatua","elder"],["kauri","large native conifer"],["koha","gift"],["kohanga reo","language nest"],["mahi","work"],["mana","reputation"],["manuhiri","guests"],["maori","indigenous inhabitants of New Zealand"],["marae","meeting grounds"],["maunga","mountain"],["moa","extinct bird"],["moana","sea"],["motu","island"],["nui","large"],["pa","hill fort"],["pakeha","european"],["pounamu","greenstone"],["puku","stomach"],["rangatira","boss"],["taihoa","to wait"],["tama","som"],["tamahine","daughter"],["tamariki","children"],["tane","man"],["tangi","funeral"],["taonga","anything precious"],["tapu","sacred"],["te reo maori","maori language"],["tipuna","ancestor"],["wahini","woman"],["wai","water"],["waka","canoe"],["whakapapa","genealogy"],["iti","small"],["haka","dance"]]

questions = random.sample(quiz,k=10)

for i in range(0,10):
    quiz_op.remove(questions[i])
    
count = 0

for i in range(0,10):
    
    print("\n\n"+str(i+1),'.What is the translation for "%s"?\n'%questions[i][0])
    options = random.sample(quiz_op,k=4)
    
    a = random.randint(0,3)
    
    questions[i][1], options[a][1] = options[a][1], questions[i][1]
    
    if mode == 'yes' or mode == 'YES':
        for num in range(0,4):print(str(num+1)+'.'+options[num][1])
         
    user = input("\nENTER OPTION 1,2,3,4 or Type your answer: ")
    
    if user == str(a+1) or user.lower() == options[a][1]:
        print("\n==> CORRECT!!!\n")
        count = count+1
        
    else:print('\n==> WRONG!!!\n\n Correct Answer is "%s"\n'%options[a][1])

    options[a][1],questions[i][1]  = questions[i][1], options[a][1]
    
    print("\n*****************************************************************\n")
    
print("YOUR SCORE : ",count)
