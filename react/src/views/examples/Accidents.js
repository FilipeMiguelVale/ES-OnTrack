/*!

=========================================================
* Argon Dashboard React - v1.1.0
=========================================================

* Product Page: https://www.creative-tim.com/product/argon-dashboard-react
* Copyright 2019 Creative Tim (https://www.creative-tim.com)
* Licensed under MIT (https://github.com/creativetimofficial/argon-dashboard-react/blob/master/LICENSE.md)

* Coded by Creative Tim

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

*/
import React from "react";
import {Redirect} from "react-router-dom";
import "../../assets/css/custom.css"

// reactstrap components
import {
    Badge,
    Card,
    CardHeader,
    Media,
    Table,
    Container,
    Row,
    Button, Col, Progress, DropdownToggle, DropdownMenu, DropdownItem, ButtonDropdown,
} from "reactstrap";
// core components
import Header from "../../components/Headers/Header.js";
import Maps from "./Maps_Component.js"


function fix_date(st) {
  let date = st.split('T');
  let year = date[0];
  let time  = date[1].split('.')[0];
  return [year,time];
}

class Accidents extends React.Component {

  constructor(props) {
    super(props);

    this.timer = null

    this.toggleDrop1 = this.toggleDrop1.bind(this);
    this.changeValueDrop1 = this.changeValueDrop1.bind(this);

    this.toggleDrop2 = this.toggleDrop2.bind(this);
    this.changeValueDrop2 = this.changeValueDrop2.bind(this);

    this.toggleDrop3 = this.toggleDrop3.bind(this);
    this.changeValueDrop3 = this.changeValueDrop3.bind(this);

    this.state = {
      table_data : [],
      table_buttons:[],
      curent_page:1,
      num_accidents:0,
      num_to_show:10,
      dropDown1Value: "Date/Hour",
      dropdownIndex:"between",
      dropdownIndex2:"All",
      dropdownIndex3:"Descending",
      dropDown1Open: false,
      dropDown2Value: "Time",
      dropDown2Open: false,
      dropDown3Value: "Order by",
      dropDown3Open: false,
      error:false,
      role:1

    }
  }

  redirect_to_details = (index) => {
    return <Redirect to={`/admin/accident_details/${index}`}/>
  }

  getData = async (id) => {
      try {
           const response = await fetch(
              `/num_accidents?quantity=${this.state.dropdownIndex2}`
          );

          const result = await response.json();
          this.setState(
              prevState => (
                  {
                      num_accidents: result
                  }
              )
          );
          const response1 = await fetch(
              `/range_accidents?id=${id}&filter=${this.state.dropdownIndex}&quantity=${this.state.dropdownIndex2}&order=${this.state.dropdownIndex3}`
          );

          const result1 = await response1.json();
          if(result1.length===0)
              this.setState(
             prevState => (
                 {
                     error: "No accidents to Show"
                 }
             )
         );
          else
              this.setState(
                  prevState => (
                      {
                          table_data: result1,
                          error: false
                      }
                  )
              );
          console.log(result1)
          // const req = await fetch(
          //     '/home'
          // );

          // const rep = await req.json();
          // console.log(rep)
          // this.setState(
          //     prevState => (
          //         {
          //             role:rep["role"]
          //         }
          //     )
          // );
      }
    catch(e){
         this.setState(
             prevState => (
                 {
                     error: "No accidents to Show"
                 }
             )
         );
     }
  }

  /* Sets status colors
  *     0 -> red (this accident was not answered yet)
  *     1 -> yellow (emergency services are on their way)
  *     2 -> green (accident resolved)
  */
  setStatus = (value) => {
      if (value === 2) {
          return (
              <Badge color="" className="badge-dot badge-lg">
                <i className="bg-lime" />
              </Badge>
          )
      }
      else if (value === 1){
          return (
              <Badge color="" className="badge-dot badge-lg">
                <i className="bg-yellow" />
              </Badge>
          )
      }
      else {
          return (
              <Badge color="" className="badge-dot badge-lg">
                <i className="bg-red" />
              </Badge>
          )
      }
  }
  getBarColor(damage){
      if(damage<30){return"bg-gradient-success"
      }else if(damage < 45){return"bg-gradient-info"
      }else if(damage < 75){return"bg-gradient-warning"
      }else{return"bg-gradient-danger"}
  }

  handleDelete(id){
    fetch('/delete_accident/' + id,{method: 'POST'}).then(
      response => response.json()
    ).then(
     result =>{
      console.log(result)
      this.getData()
     }
    )

  }

  renderArray = (value,index) => {
    return(
      <tr key={index} >
        <th scope="row">
          <span className="mb-0 text-sm">
            {fix_date(value["date"])[0]}<br/>{fix_date(value["date"])[1]}
          </span>
        </th>
        <th scope="row" width="5%">
          <span className="mb-0 text-sm">
            {value["location"]["address"].substring(0,45)}
          </span>
        </th>
        <th scope="row" width="5%">
          <span className="mb-0 text-sm">
            {value["city"]}
          </span>
        </th>
        <th scope = "row" style={{textAlign:"center"}}>
          <span className="mb-0 text-sm">
            {value["n_cars_involved"]}
          </span>
        </th>
        <th scope = "row" style={{textAlign:"center"}}>
          <span className="mb-0 text-sm">
            {value["n_people"]}
          </span>
        </th>
        <th scope = "row" style={{textAlign:"center"}}>
          <span className="mb-0 text-sm">
            {value["n_people_injured"]}
          </span>
        </th>
       <th scope = "row" style={{textAlign:"center"}}>
           <span className="mr-2">{value["damage"]}</span>
           <div>
               <Progress
                  max="100"
                  value={value["damage"]}
                  barClassName={this.getBarColor(value["damage"])}
                />
            </div>
        </th>
        <th scope = "row" style={{textAlign:"center"}}>
            {this.setStatus(value["status"])}
        </th>
        <th scope = "row" style={{textAlign:"center"}}>
            <Button
                className="icon icon-shape bg-transparent border-default text-white rounded-circle"
                href={`/#admin/accident_details/${value["id"]}`}
                onClick={this.redirect_to_details.bind(this,value['id'])}
            >
              <i className="fas fa-ellipsis-h"/>
            </Button>
            {/* delete button */}
           {this.state.role==0 && (
           <Button
                className="icon icon-shape border-default bg-transparent text-danger "
                type="button"
                onClick={(e) => this.handleDelete(value['id'])}
            >
             <i className="fas fa-trash"/>
           </Button>)}
        </th>
      </tr>
    )
  }

  renderButtons(){

     if(this.state.num_accidents > this.state.num_to_show){
      const Buttons = []
      if(this.state.curent_page >1)
        Buttons.push(<li className="page-item"><a className="page-link" onClick={(e)=>this.handleClick(e,this.state.curent_page-1)}><i
                       className="fas fa-angle-left"></i></a></li>)
      for (let i = 1; i < Math.ceil(this.state.num_accidents/this.state.num_to_show)+1; i++) {
        if(i==this.state.curent_page){
          Buttons.push(<li className="page-item active"><a className="page-link" onClick={(e)=>this.handleClick(e,`${i}`)}>{i}</a></li>)
        }
        else{
          Buttons.push(<li className="page-item"><a className="page-link" onClick={(e)=>this.handleClick(e,`${i}`)}>{i}</a></li>)
        }
      }
      if(this.state.curent_page < Math.ceil(this.state.num_accidents/this.state.num_to_show) )
        Buttons.push(<li className="page-item"><a className="page-link" onClick={(e)=>this.handleClick(e,parseInt(this.state.curent_page)+1)}><i
                       className="fas fa-angle-right"></i></a></li>)

      return(
              <div className="row justify-content-center">
                 <nav aria-label="Page navigation example">
                     <ul className="pagination">
                        {Buttons}
                     </ul>
                 </nav>
              </div>
      )}
      else{
          return
      }
  }

  componentDidMount() {
    this.getData(this.state.curent_page);
    this.timer = setInterval(() => this.getData(this.state.curent_page), 10000)
  }

  componentWillUnmount(){
    clearInterval(this.timer)
    this.timer = null
  }

  handleClick = (e,id) => {
    e.preventDefault();
    this.state.curent_page=id
    this.getData(id);
  };

 /* DropDown functions */
  toggleDrop1() {
    this.setState({dropDown1Open: !this.state.dropDown1Open});
  }

  toggleDrop2() {
      this.setState({dropDown2Open: !this.state.dropDown2Open});
  }

  toggleDrop3() {
      this.setState({dropDown3Open: !this.state.dropDown3Open});
  }

  changeValueDrop1(e,id) {

      const a = ["","between","cars","people","injured","severity","status"]
      this.state.dropDown1Value= e.currentTarget.textContent
      this.state.dropdownIndex= `${a[id]}`
      this.getData(this.state.curent_page)
  }

  changeValueDrop2(e,id) {
      this.state.dropDown2Value= e.currentTarget.textContent
      this.state.dropdownIndex2= `${id}`
      this.getData(this.state.curent_page)

  }

  changeValueDrop3(e,id) {
      this.state.dropDown3Value= e.currentTarget.textContent
      this.state.dropdownIndex3= `${id}`
      this.getData(this.state.curent_page)
  }


  /**************************/

  render() {
    if(this.state.error ){
        return (
         <>
        <Header />
        {/* Page content */}
        <Container className="mt--7" fluid>
          {/* Dark table */}
          <Row className="mt-5">
            <div className="col">
              <Card className="bg-default shadow">
                <CardHeader className="bg-transparent border-0">
                  <Row >
                    <Col>
                      <div className="row ml">
                        <h1 className="text-white mb-0" style={{ paddingLeft: 20}} >Accidents</h1>
                      </div>
                    </Col>
                    <Col >
                      {this.renderButtons()}
                    </Col>
                    <Col>
                      <div className="row justify-content-end">
                        <h4 className="mr-2 mt-2 text-white">Sort by: </h4>
                        <ButtonDropdown isOpen={this.state.dropDown1Open} toggle={this.toggleDrop1}>
                          <DropdownToggle caret>
                            {this.state.dropDown1Value}
                          </DropdownToggle>
                          <DropdownMenu right>
                            <DropdownItem onClick={(e)=>this.changeValueDrop1(e,1)}>Date/Hour</DropdownItem>
                            <DropdownItem onClick={(e)=>this.changeValueDrop1(e,2)}>N?? cars</DropdownItem>
                            <DropdownItem onClick={(e)=>this.changeValueDrop1(e,3)}>N?? people</DropdownItem>
                            <DropdownItem onClick={(e)=>this.changeValueDrop1(e,4)}>N?? injured</DropdownItem>
                            <DropdownItem onClick={(e)=>this.changeValueDrop1(e,5)}>Severity</DropdownItem>
                            <DropdownItem onClick={(e)=>this.changeValueDrop1(e,6)}>Status</DropdownItem>
                          </DropdownMenu>
                        </ButtonDropdown>
                        <ButtonDropdown className={"mr-2 ml-2"} isOpen={this.state.dropDown2Open} toggle={this.toggleDrop2}>
                          <DropdownToggle caret>
                            {this.state.dropDown2Value}
                          </DropdownToggle>
                          <DropdownMenu right>
                            <DropdownItem onClick={(e)=>this.changeValueDrop2(e,"Today")}>Today</DropdownItem>
                            <DropdownItem onClick={(e)=>this.changeValueDrop2(e,"Yesterday")}>Yesterday</DropdownItem>
                            <DropdownItem onClick={(e)=>this.changeValueDrop2(e,"Last Month")}>Last Month</DropdownItem>
                            <DropdownItem onClick={(e)=>this.changeValueDrop2(e,"All")}>All</DropdownItem>
                          </DropdownMenu>
                        </ButtonDropdown>
                        <ButtonDropdown isOpen={this.state.dropDown3Open} toggle={this.toggleDrop3}>
                          <DropdownToggle caret>
                            {this.state.dropDown3Value}
                          </DropdownToggle>
                          <DropdownMenu right>
                            <DropdownItem onClick={(e)=>this.changeValueDrop3(e,"Ascending")}>Ascending</DropdownItem>
                            <DropdownItem onClick={(e)=>this.changeValueDrop3(e,"Descending")}>Descending</DropdownItem>
                          </DropdownMenu>
                        </ButtonDropdown>
                      </div>
                    </Col>
                  </Row>
                </CardHeader>
                <h1 className="text-white mb-0" style={{textAlign:"center"}}> {this.state.error}</h1>
                  <br/>
              </Card>
            </div>
          </Row>
        </Container>
             </>);
    }else{
    return (
      <>
        <Header />
        {/* Page content */}
        <Container className="mt--7" fluid>
          {/* Dark table */}
          <Row className="mt-5">
            <div className="col">
              <Card className="bg-default shadow">
                <CardHeader className="bg-transparent border-0">
                  <Row >
                    <Col>
                      <div className="row ml">
                        <h1 className="text-white mb-0" style={{ paddingLeft: 20}} >Accidents</h1>
                      </div>
                    </Col>
                    <Col >
                      {this.renderButtons()}
                    </Col>
                    <Col>
                      <div className="row justify-content-end">
                        <h4 className="mr-2 mt-2 text-white">Sort by: </h4>
                        <ButtonDropdown isOpen={this.state.dropDown1Open} toggle={this.toggleDrop1}>
                          <DropdownToggle caret>
                            {this.state.dropDown1Value}
                          </DropdownToggle>
                          <DropdownMenu right>
                            <DropdownItem onClick={(e)=>this.changeValueDrop1(e,1)}>Date/Hour</DropdownItem>
                            <DropdownItem onClick={(e)=>this.changeValueDrop1(e,2)}>N?? cars</DropdownItem>
                            <DropdownItem onClick={(e)=>this.changeValueDrop1(e,3)}>N?? people</DropdownItem>
                            <DropdownItem onClick={(e)=>this.changeValueDrop1(e,4)}>N?? injured</DropdownItem>
                            <DropdownItem onClick={(e)=>this.changeValueDrop1(e,5)}>Severity</DropdownItem>
                            <DropdownItem onClick={(e)=>this.changeValueDrop1(e,6)}>Status</DropdownItem>
                          </DropdownMenu>
                        </ButtonDropdown>
                        <ButtonDropdown className={"mr-2 ml-2"} isOpen={this.state.dropDown2Open} toggle={this.toggleDrop2}>
                          <DropdownToggle caret>
                            {this.state.dropDown2Value}
                          </DropdownToggle>
                          <DropdownMenu right>
                            <DropdownItem onClick={(e)=>this.changeValueDrop2(e,"Today")}>Today</DropdownItem>
                            <DropdownItem onClick={(e)=>this.changeValueDrop2(e,"Yesterday")}>Yesterday</DropdownItem>
                            <DropdownItem onClick={(e)=>this.changeValueDrop2(e,"Last Month")}>Last Month</DropdownItem>
                            <DropdownItem onClick={(e)=>this.changeValueDrop2(e,"All")}>All</DropdownItem>
                          </DropdownMenu>
                        </ButtonDropdown>
                        <ButtonDropdown isOpen={this.state.dropDown3Open} toggle={this.toggleDrop3}>
                          <DropdownToggle caret>
                            {this.state.dropDown3Value}
                          </DropdownToggle>
                          <DropdownMenu right>
                            <DropdownItem onClick={(e)=>this.changeValueDrop3(e,"Ascending")}>Ascending</DropdownItem>
                            <DropdownItem onClick={(e)=>this.changeValueDrop3(e,"Descending")}>Descending</DropdownItem>
                          </DropdownMenu>
                        </ButtonDropdown>
                      </div>
                    </Col>
                  </Row>
                </CardHeader>
                <Table bordered
                  className="align-items-center table-dark table-responsive"
                  hover
                >
                  <thead className="thead-dark">
                    <tr >
                      <th scope="col " style={{textAlign:"center"}}>
                       <div align="center" className="icon icon-shape bg-transparent text-white rounded-circle">
                         <i className="fas fa-calendar-alt"/>
                       </div>
                      <div >
                         <span className="ml-1">Date/Hour</span>
                      </div>
                      </th>
                      <th scope="col"  style={{textAlign:"center"}}>
                       <div  className="icon icon-shape bg-transparent text-white rounded-circle" >
                         <i  className="fas fa-map-marked-alt"/>
                       </div>
                       <div >
                         <span className="ml-1">Location</span>
                       </div>
                      </th>
                      <th scope="col"  style={{textAlign:"center"}}>
                       <div  className="icon icon-shape bg-transparent text-white rounded-circle" >
                         <i  className="fas fa-building"/>
                       </div>
                       <div >
                         <span className="ml-1">City</span>
                       </div>
                      </th>
                      <th scope="col" style={{textAlign:"center"}}>
                       <div className="icon icon-shape bg-transparent text-white rounded-circle">
                         <i className="fas fa-car"/>
                       </div>
                       <div>
                         <span className="ml-1">N?? cars</span>
                       </div>
                      </th>
                      <th scope="col" style={{textAlign:"center"}}>
                       <div className="icon icon-shape bg-transparent text-white rounded-circle">
                         <i className="fas fa-users"/>
                       </div>
                      <div>
                         <span className="ml-1">N?? people</span>
                       </div>
                      </th>
                      <th scope="col" style={{textAlign:"center"}}>
                        <div className="icon icon-shape bg-transparent text-white rounded-circle">
                          <i className="fas fa-user-injured"/>
                       </div>
                      <div>
                       <span className="ml-1">N??injured</span>
                      </div>
                      </th>
                      <th scope="col"style={{textAlign:"center"}}>
                       <div className="icon icon-shape bg-transparent text-white rounded-circle">
                         <i className="fas fa-exclamation-triangle"/>
                       </div>
                      <div>
                       <span className="ml-1">Severity</span>
                      </div>
                      </th>
                      <th scope="col"style={{textAlign:"center"}}>
                       <div className="icon icon-shape bg-transparent text-white rounded-circle">
                         <i className="fas fa-flag"/>
                       </div>
                      <div>
                       <span className="ml-1">Status</span>
                      </div>
                      </th>
                      <th scope="col" style={{textAlign:"center"}}   />

                    </tr>
                  </thead>
                  <tbody>
                    {this.state["table_data"].map(this.renderArray)}
                  </tbody>
                </Table>
                <CardHeader className="bg-transparent border-0">
                  <Row >
                    <Col className="row justify-content-center">
                      {this.renderButtons()}
                    </Col>
                  </Row>
                </CardHeader>
              </Card>
            </div>
          </Row>
        </Container>
      </>
    );
  }}
}

export default Accidents;
